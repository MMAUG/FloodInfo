package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.rest.client.RESTClient;
import org.mmaug.InfoCenter.widgets.ZgTextView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsDetailActivity extends AppCompatActivity {
  @Bind(R.id.tv_news_title) ZgTextView tvNewsTitle;
  @Bind(R.id.tv_news_description) ZgTextView tvNewsDescription;
  News n;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

    if (!getIntent().getExtras().isEmpty()) {
      n = (News) getIntent().getSerializableExtra("news");
      tvNewsTitle.setText(n.getTitle());
      tvNewsDescription.setText(n.getDescription());
    }
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
    }else{
      RESTClient.getInstance().getService().reportNews(0, new Callback<JsonObject>() {
        @Override public void success(JsonObject jsonObject, Response response) {
          if(response.getStatus() !=200){
            Toast.makeText(getApplicationContext(),"Report Fail",Toast.LENGTH_LONG).show();
          }
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }

    return super.onOptionsItemSelected(item);
  }
}
