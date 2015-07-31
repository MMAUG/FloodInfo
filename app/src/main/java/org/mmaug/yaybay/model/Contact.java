package org.mmaug.yaybay.model;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class Contact {
  private String name;
  private String phone;
  private String description;
  private String fbUrl;

  public Contact(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFbUrl() {
    return fbUrl;
  }

  public void setFbUrl(String fbUrl) {
    this.fbUrl = fbUrl;
  }
}
