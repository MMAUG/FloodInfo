package org.mmaug.InfoCenter.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author SH (swanhtet1992@gmail.com)
 */
public class NkTextView extends TextView {
  public NkTextView(Context context) {
    super(context);
    setFont(context);
  }

  private void setFont(Context context) {
    setTypeface(Typeface.createFromAsset(context.getAssets(), "namkhone.ttf"));
  }

  public NkTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setFont(context);
  }

  public NkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setFont(context);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public NkTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    setFont(context);
  }
}
