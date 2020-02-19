package com.corneliouzbett.mpesasdk;

import static com.corneliouzbett.mpesasdk.util.MpesaUtils.okHttpClient;

import java.io.IOException;

import com.corneliouzbett.mpesasdk.core.interceptor.AuthenticationInterceptor;
import com.corneliouzbett.mpesasdk.core.interceptor.TokenInterceptor;
import com.corneliouzbett.mpesasdk.core.service.MpesaService;
import com.corneliouzbett.mpesasdk.common.Log;
import com.corneliouzbett.mpesasdk.enums.Mode;
import com.corneliouzbett.mpesasdk.exception.AuthenticationException;
import com.corneliouzbett.mpesasdk.util.MpesaUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class to initialize/setup Mpesa environment
 */
public class Mpesa {

	private static String consumerKey;

	private static String consumerSecret;

	private static Mode mode;

	private static String accessToken;

	private static Log log = new Log(Mpesa.class);

	/**
	 * Initialize the Mpesa environment.
	 *
	 * @param consumerKey    account to use
	 * @param consumerSecret auth token for the account
	 */
	static void init(String consumerKey, String consumerSecret, Mode mode) {
		Mpesa.setConsumerKey(consumerKey);
		Mpesa.setConsumerSecret(consumerSecret);
		Mpesa.setMode(mode);
		authenticate();
	}

	private static void setConsumerKey(String consumerKey) {
		if (consumerKey == null) {
			throw new AuthenticationException("Consumer key cannot be null");
		}
		Mpesa.consumerKey = consumerKey;
	}

	private static void setConsumerSecret(String consumerSecret) {
		if (consumerSecret == null) {
			throw new AuthenticationException("Consumer secret cannot be null");
		}
		Mpesa.consumerSecret = consumerSecret;
	}

	private static void setMode(Mode mode) {
		Mpesa.mode = mode;
	}

	private static void setAccessToken(String accessToken) {
		Mpesa.accessToken = accessToken;
	}

	private static String getAccessToken() {
		return accessToken;
	}

	private static void authenticate() {
		Request request = new Request.Builder()
				.url(getAuthUrl())
				.get().build();

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new TokenInterceptor(consumerKey, consumerSecret)).build();
		okHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				log.info("Authentication failed");
				throw new AuthenticationException("Unable to authenticate", e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) {
					throw new AuthenticationException("Unexpected code" + response);
				} else {
					if (response.body() != null) {
						JSONObject token = new JSONObject(response.body().string());
						log.info(token.toString());
						Mpesa.setAccessToken(token.getString("access_token"));
					}
				}
			}
		});
	}

	private static Retrofit mpesaRestClient() {

		return new Retrofit.Builder()
				.baseUrl(MpesaUtils.getBaseUrl(mode))
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient().addInterceptor(
						new AuthenticationInterceptor(Mpesa.getAccessToken())).build())
				.build();
	}

	private static MpesaService mpesaService() {
		return mpesaRestClient().create(MpesaService.class);
	}

	private static String getAuthUrl() {
		return MpesaUtils.getBaseUrl(mode) + "/oauth/v1/generate?grant_type=client_credentials";
	}

}
