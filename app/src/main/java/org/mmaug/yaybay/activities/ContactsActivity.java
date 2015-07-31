package org.mmaug.yaybay.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import org.mmaug.yaybay.adapter.ContactAdapter;
import org.mmaug.yaybay.base.BaseListActivity;
import org.mmaug.yaybay.model.Contact;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class ContactsActivity extends BaseListActivity {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * Implement this with the Custom Adapters of your choice.
   *
   * @return custom RecyclerView.Adapter
   */
  @Override protected Adapter getAdapter() {
    final ContactAdapter adapter = new ContactAdapter();
    adapter.setOnItemClickListener(this);    //This is the code to provide a sectioned grid

    ArrayList<Contact> Contacts = new ArrayList<Contact>();
    //todo replace with real data list
    // Contacts.addAll(insert real data here);
    adapter.setContacts(Contacts);

    return adapter;
  }

  /**
   * Implement this with the ItemDecoration of your choice.
   *
   * if you don't want decoration, @return null
   * else @return ItemDecoration
   */
  @Override protected ItemDecoration getItemDecoration() {
    return null;
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

  }
}
