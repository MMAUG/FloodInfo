package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.regex.Pattern;
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
      tvFbUrl.setLinksClickable(true);
      tvDescription.setText(c.getDescription());
      tvContactPhone.setText(convertToEnglishNo(c.getPhone()));
      Pattern phonePattern = Pattern.compile("\\d+");
      Linkify.addLinks(tvContactPhone, phonePattern, "tel: ");
      Linkify.addLinks(tvDescription, Linkify.WEB_URLS);
    }
  }

  private String convertToEnglishNo(String input) {
    input = input.replaceAll("[\u1040]", "0");
    input = input.replaceAll("[\u1041]", "1");
    input = input.replaceAll("[\u1042]", "2");
    input = input.replaceAll("[\u1043]", "3");
    input = input.replaceAll("[\u1044]", "4");
    input = input.replaceAll("[\u1045]", "5");
    input = input.replaceAll("[\u1046]", "6");
    input = input.replaceAll("[\u1047]", "7");
    input = input.replaceAll("[\u1048]", "8");
    input = input.replaceAll("[\u1049]", "9");
    return input;
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
