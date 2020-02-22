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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.corneliouzbett.mpesasdk.Mpesa;
import com.corneliouzbett.mpesasdk.common.Log;
import com.corneliouzbett.mpesasdk.common.MpesaConstants;
import com.corneliouzbett.mpesasdk.core.interceptor.AuthenticationInterceptor;
import com.corneliouzbett.mpesasdk.core.interceptor.TokenInterceptor;
import com.corneliouzbett.mpesasdk.core.rest.params.AccessToken;
import com.corneliouzbett.mpesasdk.exception.AuthenticationException;
import com.corneliouzbett.mpesasdk.exception.MpesaException;
import jdk.nashorn.internal.ir.annotations.Immutable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

	private AccessToken accessToken;

	private String consumerKey;

	private String consumerSecret;

	private Boolean isLive = false;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private Log log = new Log(MpesaClient.class);

	//Connection timeout duration
	private final int CONNECT_TIMEOUT = 60 * 1000;

	//Connection Read timeout duration
	private final int READ_TIMEOUT = 60 * 1000;

	//Connection write timeout duration
	private final int WRITE_TIMEOUT = 60 * 1000;

	private Retrofit restClient;

	/**
	 * Public constructor, for authentication with access token Use this constructor when you want to access Mpesa Daraja API
	 * using access token
	 * <pre>
	 *     final Mpesa myMpesa = new MpesaClient(
	 *     "<--your access token here --->"
	 *     );
	 * </pre>
	 *
	 * @param authToken authentication token
	 */
	public MpesaClient(AccessToken authToken) {
		if (authToken != null) {
			this.restClient = new Retrofit.Builder()
					.baseUrl(MpesaConstants.SANDBOX_DOMAIN)
					.addConverterFactory(GsonConverterFactory.create())
					.client(this.configureClient().newBuilder().addInterceptor(
							new AuthenticationInterceptor(authToken.getAccessToken())).build())
					.build();
		}
	}

	/**
	 * Public constructor, for authentication with access token Use this constructor when you want to access Mpesa Daraja API
	 * using access token
	 * <pre>
	 *     final Mpesa myMpesa = new MpesaClient(
	 *     "<--your access token here --->"
	 *     );
	 * </pre>
	 *
	 * @param authToken authentication token
	 * @param isLive    checks if app is live
	 */
	public MpesaClient(AccessToken authToken, Boolean isLive) {
		this.accessToken = authToken;
		this.restClient = new Retrofit.Builder()
				.baseUrl(getBaseUrl(isLive))
				.addConverterFactory(GsonConverterFactory.create())
				.client(this.configureClient().newBuilder().addInterceptor(
						new AuthenticationInterceptor(this.accessToken.getAccessToken())).build())
				.build();
	}

	/**
	 * Public constructor, for HTTP Basic authentication Use this constructor you want to access Mpesa Daraja API using
	 * consumerKey and consumerSecret For instance,
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
		this.restClient = new Retrofit.Builder()
				.baseUrl(MpesaConstants.SANDBOX_DOMAIN)
				.addConverterFactory(GsonConverterFactory.create())
				.client(this.configureClient().newBuilder().addInterceptor(
						new TokenInterceptor(consumerKey, consumerSecret)).build())
				.build();
	}

	public MpesaClient(String consumerKey, String consumerSecret, Boolean isLive) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.isLive = isLive;
	}

	private String getBaseUrl(Boolean isLive) {
		return isLive ? MpesaConstants.PRODUCTION_DOMAIN : MpesaConstants.SANDBOX_DOMAIN;
	}

	private OkHttpClient configureClient() {
		return new OkHttpClient.Builder()
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
				.build();
	}

	private Future<AccessToken> generateToken() {
		return this.executor.submit(this::accessToken);
	}

	private AccessToken accessToken() {
		new Retrofit.Builder()
				.baseUrl(this.getBaseUrl(isLive))
				.addConverterFactory(GsonConverterFactory.create())
				.client(this.configureClient().newBuilder().addInterceptor(
						new TokenInterceptor(this.consumerKey, this.consumerSecret)).build())
				.build().create(Auth.class).getAccessToken().enqueue(new Callback<AccessToken>() {

			@Override
			public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
				if (response.isSuccessful()) {
					log.info("Authentication successful");
					accessToken = response.body();
				}
			}

			@Override
			public void onFailure(Call<AccessToken> call, Throwable throwable) {
				log.info("Authentication failed" + throwable.getMessage());
				throw new MpesaException("Unable to authenticate", throwable);
			}
		});
		return this.accessToken;
	}

	@Override
	public AccessToken generateAccessToken() {
		return this.accessToken();
	}

	@Override
	public Mpesa authenticate() throws ExecutionException, InterruptedException {
		return new MpesaClient(this.generateToken().get());
	}

	@Override
	public AccBalance accBalance() {
		return null;
	}

	@Override
	public C2B c2b() {
		if (this.restClient != null) {
			return this.getRestClient().create(C2B.class);
		} else {
			throw new AuthenticationException("Try authenticating again");
		}
	}

	@Override
	public Retrofit getRestClient(){
		if (generateToken().isDone()) {
			this.restClient = new Retrofit.Builder()
					.baseUrl(getBaseUrl(isLive))
					.addConverterFactory(GsonConverterFactory.create())
					.client(this.configureClient().newBuilder().addInterceptor(
							new AuthenticationInterceptor(this.accessToken.getAccessToken())).build())
					.build();
		}
		return this.restClient;
	}
}
