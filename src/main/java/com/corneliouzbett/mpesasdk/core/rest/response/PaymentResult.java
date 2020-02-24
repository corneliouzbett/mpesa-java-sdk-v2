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
package com.corneliouzbett.mpesasdk.core.rest.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResult {

	/**
	 *This is the root parameter that encloses the entire result message
	 */
	@Expose
	@SerializedName("Result")
	private JsonObject result;

	/**
	 *This is a global unique identifier for the transaction request returned by
	 * the M-Pesa upon successful request submission.
	 */
	@Expose
	@SerializedName("Result")
	private String conversionId;

	/**
	 *This is a global unique identifier for the transaction request
	 * returned by the API proxy upon successful request submission.
	 */
	@Expose
	@SerializedName("Result")
	private String OriginatorConversionId;

	/**
	 *This is a message from the API that gives the status of the request processing
	 * and usually maps to a specific result code value.
	 */
	@Expose
	@SerializedName("Result")
	private String resultDec;

	/**
	 * This is a status code that indicates whether the transaction was already sent to your listener.
	 * Usual value is 0.
	 */
	@Expose
	@SerializedName("ResultType")
	private int resultType;

	/**
	 * This is a numeric status code that indicates the status of the transaction processing.
	 * 0 means success and any other code means an error occured or the transaction failed
	 */
	@Expose
	@SerializedName("ResultCode")
	private String resultCode;

	/**
	 * This is a unique M-PESA transaction ID for every payment request.
	 * Same value is sent to customer over SMS upon successful processing.
	 */
	@Expose
	@SerializedName("TransactionID")
	private String transactionId;

}
