package org.mmaug.yaybay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Ye Lin Aung on 15/07/31.
 */
public class ZgTextView extends TextView {
  public ZgTextView(Context context) {
    super(context);
    setFont(context);
  }

  public ZgTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setFont(context);
  }

  public ZgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setFont(context);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ZgTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    setFont(context);
  }

  private void setFont(Context context) {
    setTypeface(Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf"));
  }
}
