package org.mmaug.InfoCenter.model;

import java.io.Serializable;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class News implements Serializable ,Comparable<News>{
  private String title;
  private String description;
  private int id;
  private int dam_condition;
  private int river_condition;
  private int logic_alert_level;

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

  public int getDam_condition() {
    return dam_condition;
  }

  public void setDam_condition(int dam_condition) {
    this.dam_condition = dam_condition;
  }

  public int getRiver_condition() {
    return river_condition;
  }

  public void setRiver_condition(int river_condition) {
    this.river_condition = river_condition;
  }



  @Override public int compareTo(News mNew) {
    return new Double(this.getRiver_condition()).compareTo(new Double(mNew.getRiver_condition()));
  }

  public int getLogic_alert_level() {
    return this.dam_condition+this.river_condition;
  }

}
