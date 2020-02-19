package com.corneliouzbett.mpesasdk.core.interceptor;

import java.io.IOException;

import lombok.NoArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@NoArgsConstructor
public class AuthenticationInterceptor implements Interceptor {

	private String mAuthToken;

	public AuthenticationInterceptor(String mAuthToken) {
		this.mAuthToken = mAuthToken;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request().newBuilder()
				.addHeader("Authorization", "Bearer " + mAuthToken)
				.build();
		return chain.proceed(request);
	}
}
