package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.rest.client.RESTClient;
import org.mmaug.InfoCenter.utils.MMTextUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsDetailActivity extends AppCompatActivity {
  @Bind(R.id.tv_news_title) TextView tvNewsTitle;
  @Bind(R.id.tv_news_description) TextView tvNewsDescription;
  @Bind(R.id.toolbar) Toolbar toolbar;

  News news;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);

    initToolbar();

    if (!getIntent().getExtras().isEmpty()) {
      news = (News) getIntent().getSerializableExtra("news");
      tvNewsTitle.setText(news.getTitle());
      tvNewsDescription.setText(news.getDescription());

      MMTextUtils mmTextUtils = new MMTextUtils(this);
      mmTextUtils.prepareMultipleViews(news.getTitle(), tvNewsTitle, tvNewsDescription);

      tvNewsTitle.setLinksClickable(true);
      tvNewsDescription.setLinksClickable(true);
    }
  }

  private void initToolbar() {
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_report, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    } else {
      RESTClient.getInstance().getService().reportNews(news.getId(), new Callback<JsonObject>() {
        @Override public void success(JsonObject jsonObject, Response response) {
          Toast.makeText(getApplicationContext(), "Successfully report", Toast.LENGTH_LONG).show();
        }

        @Override public void failure(RetrofitError error) {
          Toast.makeText(getApplicationContext(), "Report Fail", Toast.LENGTH_LONG).show();
        }
      });
    }

    return super.onOptionsItemSelected(item);
  }
}
