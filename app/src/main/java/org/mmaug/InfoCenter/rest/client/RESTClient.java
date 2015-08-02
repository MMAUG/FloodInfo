package org.mmaug.InfoCenter.rest.client;

import org.mmaug.InfoCenter.rest.service.RESTService;
import retrofit.RestAdapter;

/**
 * Created by h3r0 on 11/25/14.
 */
public class RESTClient {

  private static RESTClient instance;
  private RESTService mService;

  public RESTClient() {
    //todo replace with real endpoint
    final RestAdapter restAdapter =
        new RestAdapter.Builder().setEndpoint("http://floodinfo-myanmar.herokuapp.com/api/").setLogLevel(
            RestAdapter.LogLevel.FULL)
            .build();
    mService = restAdapter.create(RESTService.class);
  }

  public static RESTClient getInstance() {
    if (instance == null) {
      instance = new RESTClient();
    }
    return instance;
  }

  public RESTService getService() {
    return mService;
  }
}
