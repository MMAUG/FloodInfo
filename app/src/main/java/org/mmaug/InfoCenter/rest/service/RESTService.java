package org.mmaug.InfoCenter.rest.service;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import org.mmaug.InfoCenter.model.Contact;
import org.mmaug.InfoCenter.model.News;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by h3r0 on 11/25/14.
 */
public interface RESTService {
  @GET("/v2/donation_groups") void getContacts(@Query("page")int page,Callback<JsonObject> callback);

  @GET("/v2/newsfeeds") void getNews(@Query("page") int page, Callback<JsonObject> callback);

  @FormUrlEncoded @POST("/newsfeeds") void submitNews(@Field("title") String title,
      @Field("description") String description, @Field("dam_condition") Integer damcondition,
      @Field("river_condition") Integer rivercondition, Callback<News> callback);

  @FormUrlEncoded @POST("/donation_groups") void submitContact(@Field("title") String title,
      @Field("description") String description, @Field("facebook_url") String fb,
      @Field("donation_location") String location, @Field("phone_numbers") String phone,
      Callback<News> callback);

  @GET("/newsfeeds/{id}/report_as_spam") void reportNews(@Path("id") int id,
      Callback<JsonObject> callback);
}
