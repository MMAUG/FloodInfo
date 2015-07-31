package org.mmaug.yaybay.rest.service;

import java.util.ArrayList;
import org.mmaug.yaybay.model.Contact;
import org.mmaug.yaybay.model.News;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by h3r0 on 11/25/14.
 */
public interface RESTService {
  @GET("/donation_groups") void getContacts(Callback<ArrayList<Contact>> callback);

  @GET("/newsfeeds") void getNews(Callback<ArrayList<News>> callback);
}
