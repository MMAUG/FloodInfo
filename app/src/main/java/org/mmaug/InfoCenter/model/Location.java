package org.mmaug.InfoCenter.model;

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
  public JsonObject demographic;
  //Server Value is json object i don't wanna a create new Model so Dap Tape
}
