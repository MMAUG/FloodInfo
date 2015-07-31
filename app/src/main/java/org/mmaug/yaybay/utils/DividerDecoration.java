/*
 * Copyright (c) 2015. Nex
 */

package org.mmaug.yaybay.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import mmaug.org.yaybay.R;

/**
 * Created by SH on 1/2/15.
 *
 * ItemDecoration implementation that applies and inset margin
 * around each child of the RecyclerView. It also draws item dividers
 * that are expected from a vertical list implementation, such as
 * ListView.
 */
public class DividerDecoration extends ItemDecoration {

  private static final int[] ATTRS = { android.R.attr.listDivider };

  private Drawable mDivider;
  private int mInsets;

  public DividerDecoration(Context context) {
    TypedArray a = context.obtainStyledAttributes(ATTRS);
    mDivider = a.getDrawable(0);
    a.recycle();

    mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, State state) {
    drawVertical(c, parent);
  }

  /** Draw dividers underneath each child view */
  public void drawVertical(Canvas c, RecyclerView parent) {
    final int left = parent.findViewById(R.id.tv_contact_name).getLeft();
    final int right = parent.getWidth() - parent.getPaddingRight();

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top = child.getBottom() + params.bottomMargin + mInsets;
      final int bottom = top + mDivider.getIntrinsicHeight();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(c);
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
    //We can supply forced insets for each item view here in the Rect
    outRect.set(mInsets, mInsets, mInsets, mInsets);
  }
}
