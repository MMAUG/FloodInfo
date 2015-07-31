/*
 * Copyright (c) 2014. Nex
 */

package org.mmaug.InfoCenter.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import mmaug.org.yaybay.R;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public abstract class BaseListActivity extends AppCompatActivity implements OnItemClickListener {

  @Bind(R.id.recycler_root) RecyclerView mRecyclerView;
  @Bind(R.id.progress) ProgressBar mProgressBar;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewLayoutId());
    ButterKnife.bind(this);

    if (mRecyclerView != null) {
      mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      mRecyclerView.setAdapter(getAdapter());

      if (getItemDecoration() != null) {
        mRecyclerView.addItemDecoration(getItemDecoration());
      }
    }
  }

  protected int getContentViewLayoutId() {
    return R.layout.activity_base_list;
  }

  /**
   * Implement this with the Custom Adapters of your choice.
   *
   * @return custom RecyclerView.Adapter
   */
  protected abstract Adapter getAdapter();

  /**
   * Implement this with the ItemDecoration of your choice.
   *
   * if you don't want decoration, @return null
   * else @return ItemDecoration
   */
  protected abstract ItemDecoration getItemDecoration();

  protected RecyclerView getRecyclerView() {
    return mRecyclerView;
  }

  protected ProgressBar getProgressBar() {
    return mProgressBar;
  }
}

