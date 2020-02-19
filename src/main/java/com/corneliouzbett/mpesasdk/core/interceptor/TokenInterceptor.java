package com.corneliouzbett.mpesasdk.core.interceptor;

import java.io.IOException;
import java.util.Base64;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

	private String consumerKey;

	private String consumerSecret;

	public TokenInterceptor(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		String keys = this.consumerKey + ":" + this.consumerSecret;

		Request request = chain.request().newBuilder()
				.addHeader("Authorization", "Basic "
						+ Base64.getEncoder().encodeToString(keys.getBytes()))
				.build();
		return chain.proceed(request);
	}
}
