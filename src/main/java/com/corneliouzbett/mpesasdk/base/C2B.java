package com.corneliouzbett.mpesasdk.base;

import com.corneliouzbett.mpesasdk.core.rest.request.BusinessUri;
import com.corneliouzbett.mpesasdk.core.rest.request.C2BRequest;
import com.corneliouzbett.mpesasdk.core.rest.response.MpesaResponse;
import jdk.nashorn.internal.ir.annotations.Immutable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * C2B (customer to business)
 *
 * This API entry point enables Paybill and Buy Goods merchants
 * to integrate to M-Pesa and receive real time payments notifications.
 *
 * @author corneliouz bett
 * @since 1.0.0
 */
@Immutable
public interface C2B {

	/**
	 * Register business callback and validations URL
	 *
	 * @param uri business URL request body
	 * @return {@link retrofit2.Call} mpesa response
	 */
	@POST("/mpesa/c2b/v1/registerurl")
	Call<MpesaResponse> registerUrl(@Body BusinessUri uri);

	/**
	 * This API entry point is used to make payment requests from Client to Business (C2B)
	 *
	 * @param c2b customer to business request
	 * @return {@link retrofit2.Call} mpesa response
	 */
	@POST("/mpesa/c2b/v1/simulate")
	Call<MpesaResponse> simulateTransaction(C2BRequest c2b);
}
