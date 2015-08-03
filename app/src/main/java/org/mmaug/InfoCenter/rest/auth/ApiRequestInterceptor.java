package org.mmaug.InfoCenter.rest.auth;

/**
 * Created by indexer on 8/3/15.
 */

import android.util.Base64;
import retrofit.RequestInterceptor;

/**
 * Interceptor used to authorize requests.
 */
public class ApiRequestInterceptor implements RequestInterceptor {

  @Override public void intercept(RequestFacade requestFacade) {
      final String authorizationValue = encodeCredentialsForBasicAuthorization();
      requestFacade.addHeader("Accept", "application/json");
      requestFacade.addHeader("Authorization", authorizationValue);
  }

  private String encodeCredentialsForBasicAuthorization() {
    final String userAndPassword = "doe" + ":" + "eff04a96bd" ;
    return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
  }

}
