package org.mmaug.InfoCenter.rest.service;

import java.util.ArrayList;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.model.News;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by h3r0 on 11/25/14.
 */
public interface RESTService {
  @GET("/donation_groups") void getContacts(Callback<ArrayList<Contact>> callback);

  @GET("/newsfeeds") void getNews(Callback<ArrayList<News>> callback);

  @FormUrlEncoded @POST("/newsfeeds") void submitNews(@Field("title") String title,
      @Field("description") String description, Callback<News> callback);

  @FormUrlEncoded @POST("/donation_groups") void submitContact(@Field("title") String title,
      @Field("description") String description, @Field("facebook_url") String fb,
      @Field("donation_location") String location, @Field("phone_numbers") String phone,
      Callback<News> callback);
}
