package org.mmaug.InfoCenter.rest.auth;

/**
 * Created by indexer on 8/3/15.
 */

import android.content.Context;
import android.util.Base64;
import org.mmaug.InfoCenter.R;
import retrofit.RequestInterceptor;

/**
 * Interceptor used to authorize requests.
 */
public class ApiRequestInterceptor implements RequestInterceptor {

  private Context mContext;

  public ApiRequestInterceptor(Context context) {
    this.mContext = context;
  }

  @Override public void intercept(RequestFacade requestFacade) {
    final String authorizationValue = encodeCredentialsForBasicAuthorization();
    requestFacade.addHeader("Authorization", authorizationValue);
  }

  private String encodeCredentialsForBasicAuthorization() {
    CharSequence c = String.format(mContext.getString(R.string.location_auth), "bob", "ef7db280f5");
    return "Basic " + Base64.encodeToString(c.toString().getBytes(), Base64.NO_WRAP);
  }
}
