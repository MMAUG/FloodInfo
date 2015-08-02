package org.mmaug.InfoCenter.model;

/**
 * Created by indexer on 8/3/15.
 */
public class AuthUser {
  private String username;
  private String password;

  public AuthUser() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
