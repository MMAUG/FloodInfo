package org.mmaug.InfoCenter.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by indexer on 8/3/15.
 */

public class Location {
  public int id;
  public String state;
  public String township;
  public String status;
  public String description;
  public Double lat;
  public Double lon;
  public JsonElement demographic;
}
