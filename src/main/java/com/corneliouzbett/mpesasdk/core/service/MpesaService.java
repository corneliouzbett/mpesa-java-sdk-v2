package com.corneliouzbett.mpesasdk.core.service;

import com.corneliouzbett.mpesasdk.core.rest.request.AccountBalance;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MpesaService {

	@POST("/safaricom/accountbalance/v1/query")
	Call<AccountBalance> inquiryAccountBalance(@Body AccountBalance accountBalance);

}
