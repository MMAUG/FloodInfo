package org.mmaug.InfoCenter.model;

import java.io.Serializable;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class Contact implements Serializable {
  private int id;
  private String title;
  private String phone_numbers;
  private String description;
  private String facebook_url;
  private String donation_location;

  public Contact() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPhone() {
    return phone_numbers;
  }

  public void setPhone(String phone) {
    this.phone_numbers = phone;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFbUrl() {
    return facebook_url;
  }

  public void setFbUrl(String facebook_url) {
    this.facebook_url = facebook_url;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
