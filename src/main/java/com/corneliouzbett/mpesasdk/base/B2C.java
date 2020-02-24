package com.corneliouzbett.mpesasdk.base;

import com.corneliouzbett.mpesasdk.core.rest.request.PaymentRequest;
import com.corneliouzbett.mpesasdk.core.rest.response.MpesaResponse;
import jdk.nashorn.internal.ir.annotations.Immutable;
import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Use this API to transact between an M-Pesa short code to a phone number registered on M-Pesa.
 */
@Immutable
public interface B2C {

	Call<MpesaResponse> requestPayment(@Body PaymentRequest paymentRequest);
}
