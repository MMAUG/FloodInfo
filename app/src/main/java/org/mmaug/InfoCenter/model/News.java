package org.mmaug.InfoCenter.model;

import java.io.Serializable;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class News implements Serializable {
  private String title;
  private String description;
  private int id;

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

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
}
