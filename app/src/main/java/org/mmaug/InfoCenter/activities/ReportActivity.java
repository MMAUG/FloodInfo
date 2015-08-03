package org.mmaug.InfoCenter.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.soundcloud.android.crop.Crop;
import java.io.File;
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
  @Bind(R.id.resultView) ImageView resultView;
  @Bind(R.id.edt_content) EditText edtContent;
  @Bind(R.id.rbn_water_normal) RadioButton rbnWaterNormal;
  @Bind(R.id.rbn_water_important) RadioButton rbnWaterImportant;
  @Bind(R.id.rbn_water_fload) RadioButton rbnWaterFload;
  @Bind(R.id.rbn_dam_normal) RadioButton rbnDamNormal;
  @Bind(R.id.rbn_dam_important) RadioButton rbnDamImportant;
  @Bind(R.id.rbn_dam_fload) RadioButton rbnDamFload;
  @Bind(R.id.dam_condition) TextView txtDamCondition;
  @Bind(R.id.river_condition) TextView txtRiverCondition;
  Typeface tf;
  //Normal Conditon is Unknown
  Integer river_condition = 0;
  Integer dam_condition = 0;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    ButterKnife.bind(this);
    tf = Typeface.createFromAsset(this.getAssets(), "namkhone.ttf");
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    setTypeFace();
  }

  public void setTypeFace() {
    rbnWaterImportant.setTypeface(tf);
    rbnWaterNormal.setTypeface(tf);
    rbnWaterFload.setTypeface(tf);
    rbnDamFload.setTypeface(tf);
    rbnDamImportant.setTypeface(tf);
    rbnDamNormal.setTypeface(tf);
    txtDamCondition.setTypeface(tf);
    txtRiverCondition.setTypeface(tf);
  }

  @OnClick(R.id.rbn_dam_normal) void normalCondition() {
    dam_condition = 1;
  }

  @OnClick(R.id.rbn_dam_important) void importantCondition() {
    dam_condition = 2;
  }

  @OnClick(R.id.rbn_dam_important) void floadCondition() {
    dam_condition = 3;
  }

  @OnClick(R.id.resultView) void chooseImage() {
    Crop.pickImage(this);
  }

  @OnClick(R.id.rbn_water_normal) void normalRiverCondition() {
    river_condition = 1;
  }

  @OnClick(R.id.rbn_water_important) void importantRiverCondition() {
    river_condition = 2;
  }

  @OnClick(R.id.rbn_water_important) void floadRiverCondition() {
    river_condition = 3;
  }

  public void onSubmit(View view) {
    if (TextUtils.isEmpty(edtTitle.getText()) || TextUtils.isEmpty(edtContent.getText())) {

      Snackbar snackbar =
          Snackbar.make(view, "အခ်က္အလက္ကိုျပည့္စုံစြာေျဖဆိုေပးပါရန္ ေမတၲာရပ္ခံပါတယ္",
              Snackbar.LENGTH_LONG);
      snackbar.setActionTextColor(Color.RED);
      View snackbarView = snackbar.getView();
      snackbarView.setBackgroundColor(Color.DKGRAY);
      TextView textView =
          (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
      textView.setTextColor(Color.YELLOW);
      textView.setTypeface(tf);
      snackbar.show();
      /*Toast.makeText(this, "Please write something that is worth sharing...", Toast.LENGTH_LONG)
          .show();*/
    } else {
      Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
      String title = edtTitle.getText().toString();
      String content = edtContent.getText().toString();

      RESTClient.getInstance()
          .getService()
          .submitNews(title, content, dam_condition, river_condition, new Callback<News>() {
            @Override public void success(News news, Response response) {
              Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
              finish();
            }

            @Override public void failure(RetrofitError error) {

            }
          });
    }
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent result) {
    if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
      beginCrop(result.getData());
    } else if (requestCode == Crop.REQUEST_CROP) {
      handleCrop(resultCode, result);
    }
  }

  private void beginCrop(Uri source) {
    Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
    Crop.of(source, destination).asSquare().start(this);
  }

  private void handleCrop(int resultCode, Intent result) {
    if (resultCode == RESULT_OK) {
      resultView.setImageURI(Crop.getOutput(result));
    } else if (resultCode == Crop.RESULT_ERROR) {
      Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
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
