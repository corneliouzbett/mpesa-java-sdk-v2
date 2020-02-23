/*
 * MIT License
 * Copyright (c) 2020 corneliouz bett
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction,including without limitation
 * the rights to use, copy,modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.corneliouzbett.mpesasdk.base;

import java.util.concurrent.TimeUnit;

import com.corneliouzbett.mpesasdk.Mpesa;
import com.corneliouzbett.mpesasdk.common.Log;
import com.corneliouzbett.mpesasdk.common.MpesaConstants;
import com.corneliouzbett.mpesasdk.core.interceptor.AuthenticationInterceptor;
import com.corneliouzbett.mpesasdk.core.interceptor.TokenInterceptor;
import com.corneliouzbett.mpesasdk.core.rest.params.AccessToken;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Mpesa Client, starting point to the entire library
 * <p>This is how you start communicating with Mpesa Daraja Api</p>
 *
 * @author corneliouz bett
 * @since 1.0.0
 */
@Immutable
public class MpesaClient implements Mpesa {

	private Boolean isLive = false;

	private Log log = new Log(MpesaClient.class);

	private Retrofit restClient;

	/**
	 * Public constructor, for authentication with access token
	 *
	 * Use this constructor when you want to access Mpesa Daraja API
	 * using access token
	 *
	 * <pre>
	 *     final Mpesa myMpesa = new MpesaClient(
	 *     "<--your access token here --->"
	 *     );
	 * </pre>
	 *
	 * @param authToken authentication token
	 */
	public MpesaClient(AccessToken authToken) {
		this.restClient = this.getRestClient(authToken.getAccessToken());
	}

	/**
	 * Public constructor, for HTTP Basic authentication
	 *
	 * Use this constructor you want to access Mpesa Daraja API using
	 * consumerKey and consumerSecret For instance,
	 *
	 * <pre>
	 *     final Mpesa myMpesa = new MpesaClient(
	 *     "89jMLEpzHAuW5bF28zBz7qoF332th2DB", "XKHJii8egyVXg7De"
	 *     );
	 * </pre>
	 *
	 * @param consumerKey    consumerKey
	 * @param consumerSecret consumerSecret
	 */
	public MpesaClient(String consumerKey, String consumerSecret) {
		new MpesaClient(consumerKey, consumerSecret, false);
	}

	/**
	 *
	 * @param consumerKey client consumer key
	 * @param consumerSecret client consumer secret
	 * @param isLive true means the app has been released to production
	 */
	public MpesaClient(String consumerKey, String consumerSecret, Boolean isLive) {
		this.isLive = isLive;
		this.restClient = new Retrofit.Builder()
				.baseUrl(getBaseUrl(isLive))
				.addConverterFactory(GsonConverterFactory.create())
				.client(this.configureClient().newBuilder().addInterceptor(
						new TokenInterceptor(consumerKey, consumerSecret)).build())
				.build();
	}

	private String getBaseUrl(Boolean isLive) {
		return isLive ? MpesaConstants.PRODUCTION_DOMAIN : MpesaConstants.SANDBOX_DOMAIN;
	}

	/**
	 * Configures Http Client
	 * @return OkHttpClient
	 */
	private OkHttpClient configureClient() {
		return new OkHttpClient.Builder()
				.connectTimeout(MpesaConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(MpesaConstants.READ_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(MpesaConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
				.build();
	}

	/**
	 * Gets RestClient (pre-configured retrofit Instance)
	 *
	 * @param accessToken authorization token
	 * @return Retrofit rest client pre-configured
	 */
	private Retrofit getRestClient(@NonNull String accessToken) {
		return new Retrofit.Builder()
				.baseUrl(getBaseUrl(isLive))
				.addConverterFactory(GsonConverterFactory.create())
				.client(this.configureClient().newBuilder().addInterceptor(
						new AuthenticationInterceptor(accessToken)).build())
				.build();
	}

	@Override
	public Auth authenticate() {
		return this.restClient.create(Auth.class);
	}

	@Override
	public AccBalance accBalance(@NonNull String accessToken) {
		return this.getRestClient(accessToken).create(AccBalance.class);
	}

	@Override
	public C2B c2b(@NonNull String accessToken) {
		return this.getRestClient(accessToken).create(C2B.class);
	}
}
