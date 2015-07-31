package org.mmaug.yaybay.model;

import java.io.Serializable;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class News implements Serializable {
  private String title;
  private String description;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
