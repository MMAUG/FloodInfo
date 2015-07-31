/*
 * Copyright (c) 2014. Nex
 */

package org.mmaug.InfoCenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.widgets.ZgTextView;
import org.mmaug.InfoCenter.adapter.ContactAdapter.ContactHolder;
import org.mmaug.InfoCenter.base.BaseAdapter;
import org.mmaug.InfoCenter.model.Contact;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class ContactAdapter extends BaseAdapter<ContactHolder> {

  private List<Contact> mContacts;

  public ContactAdapter() {
    mContacts = new ArrayList<>();
  }

  public void setContacts(ArrayList<Contact> Contacts) {
    mContacts.clear();
    mContacts.addAll(Contacts);
    this.notifyItemRangeInserted(0, mContacts.size() - 1);
  }

  @Override public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.item_contact_view, parent, false);

    return new ContactHolder(view, this);
  }

  @Override public void onBindViewHolder(ContactHolder holder, int position) {
    final Contact Contact = mContacts.get(position);

    holder.setContactName(Contact.getTitle());
  }

  @Override public int getItemCount() {
    return mContacts == null ? 0 : mContacts.size();
  }

  public static class ContactHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.tv_contact_name) ZgTextView mContactName;

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
}
