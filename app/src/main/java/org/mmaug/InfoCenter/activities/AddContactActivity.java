package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.rest.client.RESTClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class AddContactActivity extends AppCompatActivity {
  @Bind(R.id.edt_title) EditText edtTitle;
  @Bind(R.id.edt_fb_url) EditText edtFbUrl;
  @Bind(R.id.edt_contact_number) EditText edtContactNumber;
  @Bind(R.id.edt_detail) EditText edtDetail;
  @Bind(R.id.edt_donation_location) EditText edtLocation;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_contact);
    ButterKnife.bind(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  public void onSubmit(View view) {
    if (TextUtils.isEmpty(edtTitle.getText())
        || TextUtils.isEmpty(edtFbUrl.getText())
        || TextUtils.isEmpty(edtContactNumber.getText())) {
      Toast.makeText(this, "Please write at least contact number", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
      String title = edtTitle.getText().toString();
      String fb = edtFbUrl.getText().toString();
      String content = edtDetail.getText().toString();
      String ph = edtContactNumber.getText().toString();
      String location = edtLocation.getText().toString();

      RESTClient.getInstance()
          .getService()
          .submitContact(title, content, fb, location, ph, new Callback<News>() {
            @Override public void success(News news, Response response) {
              Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
              finish();
            }

            @Override public void failure(RetrofitError error) {

            }
          });
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
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
