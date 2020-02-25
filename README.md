## Mpesa Java SDK
[![Build Status](https://travis-ci.com/corneliouzbett/mpesa-java-sdk-v2.svg?branch=master)](https://travis-ci.com/corneliouzbett/mpesa-java-sdk-v2)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.corneliouzbett/mpesa-sdk.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.corneliouzbett%22%20AND%20a:%22mpesa-sdk%22)

A Java library for communicating with Mpesa Daraja rest API. 
 > The SDK provides convenient access to the Mpesa Daraja API from applications written in Java.

## Install
### Maven
Use the following dependency in your project to grab via Maven:
```
 <dependency>
  <groupId>com.github.corneliouzbett</groupId>
  <artifactId>mpesa-sdk</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```

### Gradle
For android developers you can install the library by adding dependency to build.gradle
```
implementation 'com.github.corneliouzbett:mpesa-sdk:1.1'
```

### Gradle for Kotlin
For kotlin developers
```
implementation("com.github.corneliouzbett:mpesa-sdk:1.1")
```

### Scala SBT
For developers using scala
```
libraryDependencies += "com.github.corneliouzbett" % "mpesa-sdk" % "1.1"
```

###
 
## Usage
The SDK needs to be initialized with your consumer key and consumer secret, which you get from the [safaricom developers portal](https://developer.safaricom.co.ke). You are required to create an app.

>You can use this SDK for either production or sandbox apps.

### Initialize the client
```java
  String consumerKey = "AXXXX-XXXX"; //consumer key
  String consumerSecret = "WEDX-XXXX-XXX-XXX"; //consumer secret

  Mpesa mpesa = new MpesaClient(consumerKey, consumerSecret); // initializing mpesa client
```

### Authentication
Authenticate mpesa client and genarate accessToken used to access authorized resources (services) e.g C2B, B2C, MExpress, AccountBalance etc
```java
mpesa.authenticate().getAccessToken().enqueue(new Callback<>() {

			@Override
			public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
				if (response.isSuccessful()) {
					String accessToken = response.body().getAccessToken();
				}
			}

			@Override
			public void onFailure(Call<AccessToken> call, Throwable throwable) {
				throw new MpesaException("Unable to authenticate Mpesa client", throwable);
			}
		});
```

### Account Balance
This API entrypoint requests for the account balance of a shortcode (Till number).
```java
  AccountBalance balance = new AccountBalance();
		balance.setCommandId("");
		balance.setInitiator("");
		balance.setIdentifierType("");
		balance.setPartyA("");
		balance.setQueueTimeOutURL("");
		balance.setResultUrl("");
		balance.setRemarks("");
		balance.setSecurityCredentials("");
  
```

### MExpress
For Lipa Na M-Pesa payment using STK Push
 * Check status of lipa na mpesa payment
 
 ```java
 OnlinePaymentStatus paymentStatus =
				new OnlinePaymentStatus(0000, "",
						Timestamp.valueOf(LocalDateTime.now()), "");
  
		mpesa.stkPush("accessToken").checkOnlinePaymentStatus(paymentStatus).enqueue(new Callback<>() {

			@Override
			public void onResponse(Call<OnlinePaymentStatusResponse> call, Response<OnlinePaymentStatusResponse> response) {

			}

			@Override
			public void onFailure(Call<OnlinePaymentStatusResponse> call, Throwable throwable) {

			}
		});
 ```
 
 * Initiate stk push
 ```java
  OnlinePayment onlinePayment = new OnlinePayment();
		onlinePayment.setAccountReference("AccRef");
		onlinePayment.setBusinessShortCode(123);
		onlinePayment.setCallbackURL("callbackUrl");
		onlinePayment.setAmount("amount");
		onlinePayment.setPartyA("partyA");
		onlinePayment.setPartyB("partyB");
		onlinePayment.setPhoneNumber("phoneNumber");
		onlinePayment.setPassword("passkey");
		onlinePayment.setTimestamp("timestamp");
		onlinePayment.setTransactionType("transaction type");
		onlinePayment.setTransactionDescription("transaction desc");
		
		mpesa.stkPush("accessToken").initiateOnlinePayment(onlinePayment).enqueue(new Callback<OnlinePaymentResponse>() {

			@Override
			public void onResponse(Call<OnlinePaymentResponse> call, Response<OnlinePaymentResponse> response) {

			}

			@Override
			public void onFailure(Call<OnlinePaymentResponse> call, Throwable throwable) {

			}
		});
 ```
 
### C2B
This API entrypoint enables Paybill and Buy Goods merchants to integrate to M-Pesa and receive real time payments notifications

### B2C
This API entrypoint enables Business to Customer (B2C) transactions between a company and customers who are the end-users of its products or services

## Note
This Mpesa SDK is currently under active development, so expect rapid changes

## License
MIT License

Copyright (c) 2020 corneliouz bett

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
