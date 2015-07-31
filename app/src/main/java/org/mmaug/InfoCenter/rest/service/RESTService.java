package org.mmaug.InfoCenter.rest.service;

import java.util.ArrayList;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.model.News;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by h3r0 on 11/25/14.
 */
public interface RESTService {
  @GET("/donation_groups") void getContacts(Callback<ArrayList<Contact>> callback);

  @GET("/newsfeeds") void getNews(Callback<ArrayList<News>> callback);
}
