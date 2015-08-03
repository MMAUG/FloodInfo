package org.mmaug.InfoCenter.rest.client;

import org.mmaug.InfoCenter.model.AuthUser;
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

  public LocationClient() {
    ApiRequestInterceptor requestInterceptor = new ApiRequestInterceptor();

    //todo replace with real endpoint
    final RestAdapter restAdapter =
        new RestAdapter.Builder()
            .setEndpoint("http://kunyi.asia/api")
            .setRequestInterceptor(requestInterceptor)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new OkClient())
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
