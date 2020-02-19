package com.corneliouzbett.mpesasdk.util;

import java.util.concurrent.TimeUnit;

import com.corneliouzbett.mpesasdk.common.MpesaConstants;
import com.corneliouzbett.mpesasdk.enums.Mode;
import okhttp3.OkHttpClient;

public class MpesaUtils {

	public static String getBaseUrl(Mode mode) {
		switch (mode) {
			case PROD:
				return MpesaConstants.PRODUCTION_DOMAIN;
			case TEST:
			default:
				return MpesaConstants.SANDBOX_DOMAIN;
		}
	}

	/**
	 * Configure OkHttpClient
	 *
	 * @return {@link okhttp3.OkHttpClient}
	 */
	public static OkHttpClient.Builder okHttpClient() {
		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
		okHttpClient.connectTimeout(15, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS);

		return okHttpClient;
	}
}
