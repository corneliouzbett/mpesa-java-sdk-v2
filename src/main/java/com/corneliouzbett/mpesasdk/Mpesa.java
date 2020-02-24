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
package com.corneliouzbett.mpesasdk;

import com.corneliouzbett.mpesasdk.base.AccBalance;
import com.corneliouzbett.mpesasdk.base.Auth;
import com.corneliouzbett.mpesasdk.base.B2C;
import com.corneliouzbett.mpesasdk.base.C2B;
import com.corneliouzbett.mpesasdk.base.MExpress;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.NonNull;

/**
 * Mpesa client, starting point to the entire library
 *
 * @author corneliouzbett
 * @since 1.0.0
 */

@Immutable
public interface Mpesa {


	/**
	 * Authenticate the client
	 */
	Auth authenticate();

	/**
	 * Get account balance
	 *
	 * @return Account Balance API entry point
	 */
	AccBalance accBalance(@NonNull String accessToken);

	/**
	 * Get customer to business entry points which includes:
	 * <ul>
	 *     <li>Register URL</li>
	 *     <li>Simulate Transaction</li>
	 * </ul>
	 *
	 * @return c2b API entry point
	 */
	C2B c2b(@NonNull String accessToken);

	/**
	 * Gets B2C entry points
	 *
	 * @param accessToken authorization Token
	 * @return b2c API entry point
	 */
	B2C b2c(@NonNull  String accessToken);

	/**
	 * For Lipa Na M-Pesa payment using STK Push
	 *
	 * @param accessToken authorization token
	 * @return MExpress entry point
	 */
	MExpress stkPush(@NonNull  String accessToken);

}
