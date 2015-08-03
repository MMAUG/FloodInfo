package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.model.Location;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.rest.client.LocationClient;
import org.mmaug.InfoCenter.utils.FileUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
  private static final String NEWS_FILE = "location.json";
  ArrayList<Location> mLocations = new ArrayList<>();
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    fetchLocation();
  }

  private void fetchLocation(){
    LocationClient.getInstance(this).getService().getLocations(new Callback<ArrayList<Location>>() {
      @Override public void success(ArrayList<Location> locations, Response response) {
        // NEVER EVER FILL UP THE LOGS WITH THE WHOLE DATA IT'S HARD TO DEBUG >_<
        mLocations = locations;
        FileUtils.saveData(MainActivity.this, FileUtils.convertToJson(mLocations), NEWS_FILE);
      }

      @Override public void failure(RetrofitError error) {
        Log.e("error ", error.getLocalizedMessage());
      }
    });
  }

  @OnClick(R.id.tv_donation_team) public void contactClick(View view) {
    Intent i = new Intent();
    i.setClass(this, ContactsActivity.class);
    startActivity(i);
  }

  @OnClick(R.id.tv_latest_news) public void newsClick(View view) {
    Intent i = new Intent();
    i.setClass(this, NewsActivity.class);
    startActivity(i);
  }

  @OnClick(R.id.tv_report) public void reportClick(View view) {
    Intent i = new Intent();
    i.setClass(this, ReportActivity.class);
    startActivity(i);
  }

  @OnClick(R.id.tv_alert_level) public void alertLevel(View view) {
    Intent i = new Intent();
    i.setClass(this, AlertActivity.class);
    startActivity(i);
  }
}
