package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
public class ReportActivity extends AppCompatActivity {
  @Bind(R.id.edt_title) EditText edtTitle;
  @Bind(R.id.edt_content) EditText edtContent;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    ButterKnife.bind(this);
  }

  public void onSubmit(View view) {
    if (TextUtils.isEmpty(edtTitle.getText()) || TextUtils.isEmpty(edtContent.getText())) {
      Toast.makeText(this, "Please write something that is worth sharing...", Toast.LENGTH_LONG)
          .show();
    } else {
      Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
      String title = edtTitle.getText().toString();
      String content = edtContent.getText().toString();

      RESTClient.getInstance().getService().submitNews(title, content, new Callback<News>() {
        @Override public void success(News news, Response response) {
          Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
          finish();
        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }
}
