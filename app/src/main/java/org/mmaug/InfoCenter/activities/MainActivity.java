package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import mmaug.org.yaybay.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void contactClick(View view) {
    Intent i = new Intent();
    i.setClass(this, ContactsActivity.class);

    startActivity(i);
  }

  public void newsClick(View view) {
    Intent i = new Intent();
    i.setClass(this, NewsActivity.class);

    startActivity(i);
  }

  public void reportClick(View view) {
    Intent i = new Intent();
    i.setClass(this, ReportActivity.class);

    startActivity(i);
  }

  public void alertLevel(View view) {
    Toast.makeText(this, "We will implement it tonight...", Toast.LENGTH_SHORT).show();
  }
}
