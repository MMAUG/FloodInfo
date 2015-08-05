/*
 * Copyright (c) 2014. Nex
 */

package org.mmaug.InfoCenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.base.BaseAdapter;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.utils.MMTextUtils;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class ContactAdapter extends BaseAdapter<BaseAdapter.BaseViewHolder> {

  public static final int CONTENT_VIEW_TYPE = 100;
  public static final int FOOTER_VIEW_TYPE = 101;

  private List<Contact> mContacts = new ArrayList<>();
  private boolean hideFooter;
  private Context mContext;

  public ContactAdapter(Context context) {
    this.mContext = context;
  }

  public void setContacts(ArrayList<Contact> Contacts) {
    mContacts.clear();
    mContacts.addAll(Contacts);
    notifyDataSetChanged();
  }

  @Override public BaseAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view;
    switch (viewType) {
      case CONTENT_VIEW_TYPE:
        view = inflater.inflate(R.layout.item_contact_view, parent, false);
        return new ContactHolder(view, this);
      case FOOTER_VIEW_TYPE:
        view = inflater.inflate(R.layout.progress_bar, parent, false);
        return new ProgressHolder(view);
    }

    return null;
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    if (holder instanceof ContactHolder) {
      final Contact contact = mContacts.get(position);
      ((ContactHolder) holder).setContactName(contact.getTitle());
      MMTextUtils mmTextUtils = new MMTextUtils(mContext);
      mmTextUtils.prepareSingleView(contact.getTitle(), ((ContactHolder) holder).mContactName);
    } else if (holder instanceof ProgressHolder) {
      if (!hideFooter) {
        ((ProgressHolder) holder).progressContainer.setVisibility(View.VISIBLE);
      } else {
        ((ProgressHolder) holder).progressContainer.setVisibility(View.GONE);
      }
    }
  }

  @Override public int getItemViewType(int position) {
    if (position != 0 && position == getItemCount() - 1) {
      return FOOTER_VIEW_TYPE;
    } else {
      return CONTENT_VIEW_TYPE;
    }
  }

  @Override public int getItemCount() {
    return mContacts == null ? 0 : mContacts.size();
  }

  public void hideFooter(boolean hideFooter) {
    this.hideFooter = hideFooter;
    notifyDataSetChanged();
  }

  public static class ContactHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.tv_contact_name) TextView mContactName;

    public ContactHolder(View itemView, ContactAdapter adapter) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
      mAdapter = adapter;
    }

    public void setContactName(String s) {
      mContactName.setText(s);
    }
  }

  public static class ProgressHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.progress_container) FrameLayout progressContainer;

    public ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
