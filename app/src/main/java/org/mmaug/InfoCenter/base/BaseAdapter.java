/*
 * Copyright (c) 2015. Nex
 */

package org.mmaug.InfoCenter.base;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import org.mmaug.InfoCenter.base.BaseAdapter.BaseViewHolder;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public abstract class BaseAdapter<VH extends BaseViewHolder> extends Adapter<VH> {

  protected OnItemClickListener mOnItemClickListener;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  protected void onItemHolderClickListener(VH itemHolder) {
    if (mOnItemClickListener != null) {
      mOnItemClickListener.onItemClick(null, itemHolder.itemView, itemHolder.getPosition(),
          itemHolder.getItemId());
    }
  }

  public static class BaseViewHolder extends ViewHolder implements OnClickListener {
    protected BaseAdapter mAdapter;

    public BaseViewHolder(View itemView) {
      super(itemView);
    }

    @Override public void onClick(View v) {
      mAdapter.onItemHolderClickListener(this);
    }
  }
}
