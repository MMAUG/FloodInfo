package org.mmaug.InfoCenter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.widgets.ZgTextView;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsDetailActivity extends AppCompatActivity {
  @Bind(R.id.tv_news_title) ZgTextView tvNewsTitle;
  @Bind(R.id.tv_news_description) ZgTextView tvNewsDescription;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);

    if (!getIntent().getExtras().isEmpty()) {
      News n = (News) getIntent().getSerializableExtra("news");
      tvNewsTitle.setText(n.getTitle());
      tvNewsDescription.setText(n.getDescription());
    }
  }
}
