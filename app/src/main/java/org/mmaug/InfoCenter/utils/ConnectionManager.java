/*
 * Copyright (c) 2015. Nex
 */

package org.mmaug.InfoCenter.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import retrofit.RetrofitError;
import retrofit.RetrofitError.Kind;

/**
 * Check device's network connectivity and speed
 *
 * @author SH (swanhtet@nexlabs.co)
 */
public class ConnectionManager {

  /**
   * Check if there is any connectivity
   */
  public static boolean isConnected(Context context) {
    NetworkInfo info = ConnectionManager.getNetworkInfo(context);
    return (info != null && info.isConnected());
  }

  /**
   * Get the network info
   */
  public static NetworkInfo getNetworkInfo(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    return cm.getActiveNetworkInfo();
  }

  /**
   * Check if there is any connectivity to a Wifi network
   */
  public static boolean isConnectedWifi(Context context) {
    NetworkInfo info = ConnectionManager.getNetworkInfo(context);
    return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
  }

  /**
   * Check if there is any connectivity to a mobile network
   */
  public static boolean isConnectedMobile(Context context) {
    NetworkInfo info = ConnectionManager.getNetworkInfo(context);
    return (info != null
        && info.isConnected()
        && info.getType() == ConnectivityManager.TYPE_MOBILE);
  }

  public static void failOnUnexpectedError(RetrofitError retrofitError) {
    // see RetrofitError.unexpectedError() for reasoning
    if (!retrofitError.getKind().equals(Kind.NETWORK) && (retrofitError.getResponse() == null)) {
      throw new RuntimeException(retrofitError.getMessage(), retrofitError);
    }
  }
}