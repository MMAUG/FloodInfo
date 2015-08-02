package org.mmaug.InfoCenter.rest.auth;

/**
 * Created by indexer on 8/3/15.
 */

import android.util.Base64;
import org.mmaug.InfoCenter.model.AuthUser;
import retrofit.RequestInterceptor;

/**
 * Interceptor used to authorize requests.
 */
public class ApiRequestInterceptor implements RequestInterceptor {
  private AuthUser user;

  @Override public void intercept(RequestFacade requestFacade) {
    if (user != null) {
      final String authorizationValue = encodeCredentialsForBasicAuthorization();
      requestFacade.addHeader("Authorization", authorizationValue);
    }
  }

  private String encodeCredentialsForBasicAuthorization() {
    final String userAndPassword = user.getUsername() + ":" + user.getPassword();
    return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
  }

  public void setUser(AuthUser user) {
    this.user = user;
  }
}
