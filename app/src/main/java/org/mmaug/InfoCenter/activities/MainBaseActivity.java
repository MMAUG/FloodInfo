package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.adapter.FragmentAdapter;
import org.mmaug.InfoCenter.fragment.ContactsFragment;
import org.mmaug.InfoCenter.fragment.NewsFragment;

/**
 * Created by poepoe on 5/8/15.
 */
public class MainBaseActivity extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.tab_layout) TabLayout mTabLayout;
  @Bind(R.id.pager) ViewPager mPager;
  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.share_fab) FloatingActionButton mFab;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    ButterKnife.bind(this);

    initUI();
  }

  private void initUI() {
    setSupportActionBar(mToolbar);
    setupViewPager(mPager);
    mTabLayout.setupWithViewPager(mPager);
    mFab.setOnClickListener(this);
  }

  private void setupViewPager(ViewPager viewPager) {
    FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
    adapter.addFrag(new NewsFragment(), "NEWS");
    adapter.addFrag(new ContactsFragment(), "Donation Groups");
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getCount());
  }

  /**
   * Called when a view has been clicked.
   *
   * @param v The view that was clicked.
   */
  @Override public void onClick(View v) {
    if (mPager.getCurrentItem() == 0) {
      Intent intentToAddNews = new Intent(this, ReportActivity.class);
      startActivity(intentToAddNews);
    } else if (mPager.getCurrentItem() == 1) {

      Intent intentToAddNews = new Intent(this, AddContactActivity.class);
      startActivity(intentToAddNews);
    }
  }
}
