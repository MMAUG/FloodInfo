package org.mmaug.InfoCenter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import org.mmaug.InfoCenter.model.Contact;

/**
 * Created by yemyatthu on 8/1/15.
 */
public class HeadlessStateFragment extends Fragment {
  public ArrayList<Contact> contacts;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }
}
