package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mmaug.org.yaybay.R;
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

public class AlertActivity extends BaseListActivity {

  private static final String LIST_STATE_FRAGEMENT = "org.mmaug.infocetner.activities.alertactivity";
  private static final String NEWS_FILE = "news.json";
  ArrayList<News> mNews = new ArrayList<>();
  private NewsAdapter mAdapter = null;
  private HeadlessStateFragment stateFragment;
  FloatingActionButton mFab;
  private int mCurrentpage =0;
  private LinearLayoutManager mLayoutManager;

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

    mLayoutManager = new LinearLayoutManager(AlertActivity.this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    getRecyclerView().setHasFixedSize(true);
    getRecyclerView().setLayoutManager(mLayoutManager);
    loadFromDisk();
    loadData(mCurrentpage);
    onFabClick();

    getRecyclerView().addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
      @Override public void onLoadMore(int current_page) {
        loadData(current_page);
      }
    });
  }

  private void onFabClick(){
    mFab = getmFab();
    mFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intentToAddNews = new Intent(AlertActivity.this, ReportActivity.class);
        startActivity(intentToAddNews);
      }
    });
  }

  /**
   * Implement this with the Custom Adapters of your choice.
   *
   * @return custom RecyclerView.Adapter
   */
  @Override protected RecyclerView.Adapter getAdapter() {
    mAdapter = new NewsAdapter();
    mAdapter.setOnItemClickListener(this);    //This is the code to provide a sectioned grid
    return mAdapter;
  }

  /**
   * Implement this with the ItemDecoration of your choice.
   *
   * if you don't want decoration, @return null
   * else @return ItemDecoration
   */
  @Override protected RecyclerView.ItemDecoration getItemDecoration() {
    return new DividerDecoration(this, DividerDecoration.VERTICAL_LIST);
  }

  private void loadData(int currentpage) {
    if (stateFragment != null && stateFragment.news != null) {
      mNews = stateFragment.news;
      stateFragment.news =
          null; //To make sure multiple call of load data method will not get only the saved contacts
      mAdapter.setNews(mNews);
    } else {
      if (ConnectionManager.isConnected(this)) {
        getProgressBar().setVisibility(View.VISIBLE);
        RESTClient.getInstance().getService().getNews(currentpage,new Callback<ArrayList<News>>() {
          @Override public void success(ArrayList<News> contacts, Response response) {
            getProgressBar().setVisibility(View.GONE);
            mNews = contacts;
            Collections.sort(mNews);
            mAdapter.setNews(mNews);
            FileUtils.saveData(AlertActivity.this, FileUtils.convertToJson(mNews), NEWS_FILE);
          }

          @Override public void failure(RetrofitError error) {
            loadFromDisk();
          }
        });
      } else {
        loadFromDisk();
        Toast.makeText(AlertActivity.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
      }
    }
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
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_refresh, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_refresh) {
      loadData(mCurrentpage);
      return true;
    } else if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void loadFromDisk(){
    Type type = new TypeToken<List<News>>() {
    }.getType();
    String contactString = FileUtils.loadData(AlertActivity.this, NEWS_FILE);
    if (contactString != null) {
      mNews.addAll(FileUtils.convertToJava(contactString, type));
      mAdapter.setNews(mNews);
    }
  }
}
