package org.mmaug.InfoCenter.rest.client;

import android.content.Context;
import com.squareup.okhttp.OkHttpClient;
import org.mmaug.InfoCenter.rest.auth.ApiRequestInterceptor;
import org.mmaug.InfoCenter.rest.service.LocationService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by indexer on 8/3/15.
 */
public class LocationClient {

  private static LocationClient instance;
  private LocationService mService;

  public LocationClient(Context context) {

    //todo replace with real endpoint
    OkHttpClient okHttpClient = new OkHttpClient();
    final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://kunyi.asia/api")
        .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
        .setRequestInterceptor(new ApiRequestInterceptor(context))
        .setClient(new OkClient(okHttpClient))
        .build();
    mService = restAdapter.create(LocationService.class);
  }

  public static LocationClient getInstance(Context context) {
    if (instance == null) {
      instance = new LocationClient(context);
    }
    return instance;
  }

  public LocationService getService() {
    return mService;
  }
}
