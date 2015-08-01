package org.mmaug.InfoCenter.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
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
  @Bind(R.id.rbn_water_normal)RadioButton rbnWaterNormal;
  @Bind(R.id.rbn_water_important)RadioButton rbnWaterImportant;
  @Bind(R.id.rbn_water_fload) RadioButton rbnWaterFload;
  @Bind(R.id.rbn_dam_normal)RadioButton rbnDamNormal;
  @Bind(R.id.rbn_dam_important)RadioButton rbnDamImportant;
  @Bind(R.id.rbn_dam_fload) RadioButton rbnDamFload;
  @Bind(R.id.dam_condition) TextView txtDamCondition;
  @Bind(R.id.river_condition)TextView txtRiverCondition;
  Typeface tf;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    ButterKnife.bind(this);
     tf = Typeface.createFromAsset(this.getAssets(), "zawgyi.ttf");
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    setTypeFace();
  }

  public void setTypeFace(){
    rbnWaterImportant.setTypeface(tf);
    rbnWaterNormal.setTypeface(tf);
    rbnWaterFload.setTypeface(tf);
    rbnDamFload.setTypeface(tf);
    rbnDamImportant.setTypeface(tf);
    rbnDamNormal.setTypeface(tf);
    txtDamCondition.setTypeface(tf);
    txtRiverCondition.setTypeface(tf);
  }

  public void onSubmit(View view) {
    if (TextUtils.isEmpty(edtTitle.getText()) || TextUtils.isEmpty(edtContent.getText())) {

      Snackbar snackbar = Snackbar
          .make(view, "အခ်က္အလက္ကိုျပည့္စုံစြာေျဖဆိုေပးပါရန္ ေမတၲာရပ္ခံပါတယ္", Snackbar.LENGTH_LONG);
      snackbar.setActionTextColor(Color.RED);
      View snackbarView = snackbar.getView();
      snackbarView.setBackgroundColor(Color.DKGRAY);
      TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
      textView.setTextColor(Color.YELLOW);
      textView.setTypeface(tf);
      snackbar.show();
      /*Toast.makeText(this, "Please write something that is worth sharing...", Toast.LENGTH_LONG)
          .show();*/
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

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
