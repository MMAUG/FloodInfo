package org.mmaug.yaybay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
}
