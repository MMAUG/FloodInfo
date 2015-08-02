package org.mmaug.InfoCenter.rest.service;

import java.util.ArrayList;
import org.mmaug.InfoCenter.model.Location;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by indexer on 8/3/15.
 */
public interface LocationService {
  //GET LOCATIONS
  @GET("/location.json") void getContacts(Callback<ArrayList<Location>> callback);
}
