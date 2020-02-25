package com.corneliouzbett.mpesasdk.base;

import com.corneliouzbett.mpesasdk.core.rest.request.AccountBalance;
import jdk.nashorn.internal.ir.annotations.Immutable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Use this API for balance inquiry.
 */
@Immutable
public interface AccBalance {

	/**
	 * Use this API to enquire the balance on an M-Pesa BuyGoods (Till Number).
	 *
	 * @return {@link retrofit2.Call} of account balance
	 */
	@POST("/mpesa/accountbalance/v1/query")
	AccBalance inquiryBalance(@Body AccountBalance accountBalance);

}
