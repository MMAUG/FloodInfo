package org.mmaug.InfoCenter.rest.client;

import org.mmaug.InfoCenter.rest.auth.ApiRequestInterceptor;
import org.mmaug.InfoCenter.rest.service.LocationService;
import retrofit.RestAdapter;

/**
 * Created by indexer on 8/3/15.
 */
public class LocationClient {

  private static LocationClient instance;
  private LocationService mService;

  public LocationClient() {

    ApiRequestInterceptor apiRequestInterceptor = new ApiRequestInterceptor();
    //todo replace with real endpoint
    final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://kunyi.asia/api")
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setRequestInterceptor(apiRequestInterceptor)
        .build();
    mService = restAdapter.create(LocationService.class);
  }

  public static LocationClient getInstance() {
    if (instance == null) {
      instance = new LocationClient();
    }
    return instance;
  }

  public LocationService getService() {
    return mService;
  }
}
