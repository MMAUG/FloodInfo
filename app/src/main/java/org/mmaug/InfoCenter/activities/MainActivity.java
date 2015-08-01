package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mmaug.org.yaybay.R;

public class MainActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
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
