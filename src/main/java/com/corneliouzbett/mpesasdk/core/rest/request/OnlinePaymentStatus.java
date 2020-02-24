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
package com.corneliouzbett.mpesasdk.core.rest.request;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Immutable
@NoArgsConstructor
@AllArgsConstructor
public class OnlinePaymentStatus {

	/**
	 * This is organizations shortCode (PayBill or Buy goods - A 5 to 6 digit account number)
	 * used to identify an organization and receive the transaction.
	 */
	@Expose
	@SerializedName("BusinessShortCode")
	private int businessCode;

	/**
	 * This is the password used for encrypting the request sent:
	 * A base64 encoded string. (The base64 string is a combination of ShortCode+Passkey+Timestamp)
	 */
	@Expose
	@SerializedName("Password")
	private String password;

	/**
	 * This is the Timestamp of the transaction, normally in the format of YEAR+MONTH+DATE+HOUR+MINUTE+SECOND (YYYYMMDDHHMMSS)
	 * Each part should be at least two digits apart from the year which takes four digits.
	 */
	@Expose
	@SerializedName("Timestamp")
	private Timestamp timestamp;

	/**
	 * This is a global unique identifier of the processed checkout transaction request.
	 */
	@Expose
	@SerializedName("CheckoutRequestID")
	private String checkRequestId;

}
