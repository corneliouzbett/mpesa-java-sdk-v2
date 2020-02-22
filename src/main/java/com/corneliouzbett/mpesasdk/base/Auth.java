package com.corneliouzbett.mpesasdk.base;

import com.corneliouzbett.mpesasdk.core.rest.params.AccessToken;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Auth {

	@GET("/oauth/v1/generate?grant_type=client_credentials")
	Call<AccessToken> getAccessToken();
}
