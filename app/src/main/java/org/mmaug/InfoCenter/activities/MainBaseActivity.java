package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.mmaug.InfoCenter.R;

/**
 * Created by poepoe on 5/8/15.
 */
public class MainBaseActivity extends AppCompatActivity {

  @Bind(R.id.tab_layout) TabLayout mTabLayout;
  @Bind(R.id.pager) ViewPager mPager;
  @Bind(R.id.toolbar) Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initUI();
  }

  private void initUI() {
    setSupportActionBar(mToolbar);
    mTabLayout.setupWithViewPager(mPager);
  }
}
