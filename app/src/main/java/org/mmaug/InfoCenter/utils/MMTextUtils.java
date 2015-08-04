package org.mmaug.InfoCenter.utils;

import android.content.Context;
import android.widget.TextView;
import mm.technomation.tmmtextutilities.mmtext;

/**
 * Created by Ye Lin Aung on 15/08/04.
 */
public class MMTextUtils {
  private Context mContext;

  public MMTextUtils(Context context) {
    this.mContext = context;
  }

  private void prepareMultipleView(TextView... textViews) {
    for (TextView textView : textViews) {
      mmtext.prepareView(mContext, textView, mm.technomation.tmmtextutilities.mmtext.TEXT_ZAWGYI,
          true, true);
    }
  }
}
