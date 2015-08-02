package org.mmaug.InfoCenter;

import android.app.Application;
import mmaug.org.yaybay.BuildConfig;
import org.mmaug.InfoCenter.utils.AnalyticsManager;

/**
 * Created by Ye Lin Aung on 15/08/02.
 */
public class InfoCenterApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    if (!BuildConfig.DEBUG) {
      AnalyticsManager.initializeAnalyticsTracker(getApplicationContext());
    }
  }
}
