package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.adapter.ContactAdapter;
import org.mmaug.InfoCenter.base.BaseListActivity;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.rest.client.RESTClient;
import org.mmaug.InfoCenter.utils.ConnectionManager;
import org.mmaug.InfoCenter.utils.DividerDecoration;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class ContactsActivity extends BaseListActivity {

  ArrayList<Contact> mContacts = new ArrayList<>();
  private ContactAdapter mAdapter = null;

  /**
   * Implement this with the Custom Adapters of your choice.
   *
   * @return custom RecyclerView.Adapter
   */
  @Override protected Adapter getAdapter() {
    mAdapter = new ContactAdapter();
    mAdapter.setOnItemClickListener(this);    //This is the code to provide a sectioned grid

    loadData();

    return mAdapter;
  }

  /**
   * Implement this with the ItemDecoration of your choice.
   *
   * if you don't want decoration, @return null
   * else @return ItemDecoration
   */
  @Override protected ItemDecoration getItemDecoration() {
    return new DividerDecoration(this, DividerDecoration.VERTICAL_LIST);
  }

  private void loadData() {
    if (ConnectionManager.isConnected(this)) {
      getProgressBar().setVisibility(View.VISIBLE);
      getRecyclerView().setVisibility(View.GONE);
      RESTClient.getInstance().getService().getContacts(new Callback<ArrayList<Contact>>() {
        @Override public void success(ArrayList<Contact> contacts, Response response) {
          getProgressBar().setVisibility(View.GONE);
          getRecyclerView().setVisibility(View.VISIBLE);
          mContacts.addAll(contacts);

          Log.e("", "Contacts: " + mContacts.get(0).getTitle());

          mAdapter.setContacts(mContacts);
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }

  /**
   * Callback method to be invoked when an item in this AdapterView has
   * been clicked.
   * <p>
   * Implementers can call getItemAtPosition(position) if they need
   * to access the data associated with the selected item.
   *
   * @param parent   The AdapterView where the click happened.
   * @param view     The view within the AdapterView that was clicked (this
   *                 will be a view provided by the adapter)
   * @param position The position of the view in the adapter.
   * @param id       The row id of the item that was clicked.
   */
  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent i = new Intent();
    i.setClass(this, ContactDetailActivity.class);

    Bundle bundle = new Bundle();
    bundle.putSerializable("contact", mContacts.get(position));
    i.putExtras(bundle);

    startActivity(i);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_contact, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_add) {
      Intent i = new Intent();
      i.setClass(this, AddContactActivity.class);

      startActivity(i);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
