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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlinePayment {

	@Expose
	@SerializedName("BusinessShortCode")
	private int businessShortCode;

	@Expose
	@SerializedName("Password")
	private String password;

	@Expose
	@SerializedName("Timestamp")
	private String timestamp;

	@Expose
	@SerializedName("TransactionType")
	private String transactionType;

	@Expose
	@SerializedName("Amount")
	private String amount;

	@Expose
	@SerializedName("PartyA")
	private String partyA;

	@Expose
	@SerializedName("PartyB")
	private String partyB;

	@Expose
	@SerializedName("PhoneNumber")
	private String phoneNumber;

	@Expose
	@SerializedName("CallbackURL")
	private String callbackURL;

	@Expose
	@SerializedName("AccountReference")
	private String accountReference;

	@Expose
	@SerializedName("TransactionDescription")
	private String transactionDescription;

}
