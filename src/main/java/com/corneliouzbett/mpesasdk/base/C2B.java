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

	@POST("/mpesa/c2b/v1/registerurl")
	Call<MpesaResponse> registerUrl(@Body BusinessUri uri);

	Call<MpesaResponse> simulateTransaction(C2BRequest c2b);
}
