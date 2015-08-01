package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.widgets.ZgTextView;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class ContactDetailActivity extends AppCompatActivity {
  @Bind(R.id.tv_contact_name) ZgTextView tvContactName;
  @Bind(R.id.tv_fb_url) TextView tvFbUrl;
  @Bind(R.id.tv_contact_phone) ZgTextView tvContactPhone;
  @Bind(R.id.tv_contact_description) ZgTextView tvDescription;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_detail);
    ButterKnife.bind(this);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

    if (!getIntent().getExtras().isEmpty()) {
      Contact c = (Contact) getIntent().getSerializableExtra("contact");
      tvContactName.setText(c.getTitle());
      tvFbUrl.setText(c.getFbUrl());
      tvContactPhone.setText(c.getPhone());
      tvDescription.setText(c.getDescription());
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
