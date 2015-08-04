package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.adapter.NewsAdapter;
import org.mmaug.InfoCenter.base.BaseListActivity;
import org.mmaug.InfoCenter.fragment.HeadlessStateFragment;
import org.mmaug.InfoCenter.listener.EndlessRecyclerOnScrollListener;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.rest.client.RESTClient;
import org.mmaug.InfoCenter.utils.ConnectionManager;
import org.mmaug.InfoCenter.utils.DividerDecoration;
import org.mmaug.InfoCenter.utils.FileUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsActivity extends BaseListActivity {

  private static final String LIST_STATE_FRAGEMENT = "org.mmaug.infocetner.activities.newsactivity";
  private static final String NEWS_FILE = "news.json";
  ArrayList<News> mNews = new ArrayList<>();
  FloatingActionButton mFab;
  LinearLayoutManager mLayoutManager;
  int totalItemCount;
  private NewsAdapter mAdapter = null;
  private HeadlessStateFragment stateFragment;
  private int mCurrentPage = 1;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    stateFragment =
        (HeadlessStateFragment) getSupportFragmentManager().findFragmentByTag(LIST_STATE_FRAGEMENT);
    if (stateFragment == null) {
      stateFragment = new HeadlessStateFragment();
      getSupportFragmentManager().beginTransaction()
          .add(stateFragment, LIST_STATE_FRAGEMENT)
          .commit();
    }
    if (stateFragment.currentPage != -10) {
      mCurrentPage = stateFragment.currentPage;
    }
    mLayoutManager = new LinearLayoutManager(NewsActivity.this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    getRecyclerView().setHasFixedSize(true);
    getRecyclerView().setLayoutManager(mLayoutManager);
    loadFromDisk();
    loadData(mCurrentPage);
    onFabClick();

    getRecyclerView().addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
      @Override public void onLoadMore(int current_page) {
        if (mCurrentPage == 1 || mCurrentPage == 10) {
          mAdapter.hideFooter(true);
          return;
        } else {
          mAdapter.hideFooter(false);
        }
        Log.d("loading", current_page + "");
        loadData(current_page);
      }
    });
  }

  /**
   * Implement this with the Custom Adapters of your choice.
   *
   * @return custom RecyclerView.Adapter
   */
  @Override protected Adapter getAdapter() {
    mAdapter = new NewsAdapter(NewsActivity.this);
    mAdapter.setOnItemClickListener(this);    //This is the code to provide a sectioned grid
    return mAdapter;
  }

  /**
   * Implement this with the ItemDecoration of your choice.
   *
   * if you don't want decoration, @return null
   * else @return ItemDecoration
   */
  @Override protected ItemDecoration getItemDecoration() {
    return new DividerDecoration(this, DividerDecoration.VERTICAL_LIST);
  }

  private void loadFromDisk() {
    Type type = new TypeToken<List<News>>() {
    }.getType();
    String contactString = FileUtils.loadData(NewsActivity.this, NEWS_FILE);
    if (contactString != null) {
      mNews.addAll(FileUtils.convertToJava(contactString, type));
      mAdapter.setNews(mNews);
    }
  }

  private void loadData(final int current_page) {
    if (stateFragment != null && stateFragment.news != null) {
      mNews = stateFragment.news;
      stateFragment.news =
          null; //To make sure multiple call of load data method will not get only the saved contacts
      mAdapter.setNews(mNews);
    } else {
      Log.d("here", "here");
      if (ConnectionManager.isConnected(this)) {
        Log.d("current page", current_page + "");
        if (current_page == 1) {
          getProgressBar().setVisibility(View.VISIBLE);
        }
        RESTClient.getInstance().getService().getNews(current_page, new Callback<JsonObject>() {
          @Override public void success(JsonObject jsonObject, Response response) {
            getProgressBar().setVisibility(View.GONE);
            //TODO WARNING NEED TO GET TOTAL NEWS COUNT
            ArrayList contacts = new ArrayList();
            //TODO total item count is total count in Load More
            if (current_page == 1) {
              totalItemCount =
                  jsonObject.get("meta").getAsJsonObject().get("page_count").getAsInt();
            }
            if (current_page < totalItemCount + 1) {
              JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
              for (int i = 0; i < jsonArray.size(); i++) {
                News singleNew = new News();
                singleNew.setId(jsonArray.get(i).getAsJsonObject().get("id").getAsInt());
                singleNew.setTitle(jsonArray.get(i).getAsJsonObject().get("title").getAsString());
                singleNew.setDescription(
                    jsonArray.get(i).getAsJsonObject().get("description").getAsString());
                contacts.add(singleNew);
              }
              if (contacts == null || contacts.size() == 0) {
                mAdapter.hideFooter(true);
                return;
              }
              Log.d("news count", mNews.size() + "");
              if (current_page == 1) {
                mNews = contacts;
                FileUtils.saveData(NewsActivity.this, FileUtils.convertToJson(mNews), NEWS_FILE);
              } else {
                mNews.addAll(contacts);
              }
              mAdapter.setNews(mNews);
              mCurrentPage++;
            }
          }

          @Override public void failure(RetrofitError error) {
            loadFromDisk();
          }
        });
      } else {
        loadFromDisk();
        Toast.makeText(NewsActivity.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void onFabClick() {
    mFab = getmFab();
    mFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intentToAddNews = new Intent(NewsActivity.this, ReportActivity.class);
        startActivity(intentToAddNews);
      }
    });
  }

  /**
   * Callback method to be invoked when an item in this AdapterView has
   * been clicked.
   * <p>
   * Implementers can call getItemAtPosition(position) if they need
   * to access the data associated with the selected item.
   *
   * @param parent The AdapterView where the click happened.
   * @param view The view within the AdapterView that was clicked (this
   * will be a view provided by the adapter)
   * @param position The position of the view in the adapter.
   * @param id The row id of the item that was clicked.
   */
  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent i = new Intent();
    i.setClass(this, org.mmaug.InfoCenter.activities.NewsDetailActivity.class);

    Bundle bundle = new Bundle();
    bundle.putSerializable("news", mNews.get(position));
    i.putExtras(bundle);

    startActivity(i);
  }

  @Override protected void onPause() {
    super.onPause();
    stateFragment.news = mNews;
    stateFragment.currentPage = mCurrentPage;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_refresh, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_refresh) {
      loadData(mCurrentPage);
      getRecyclerView().addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
        @Override public void onLoadMore(int current_page) {
          Log.d("loading", current_page + "");
          loadData(current_page);
        }
      });
      return true;
    } else if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}