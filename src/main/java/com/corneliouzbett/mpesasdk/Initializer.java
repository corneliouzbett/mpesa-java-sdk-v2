package com.corneliouzbett.mpesasdk;

import java.io.IOException;

import com.corneliouzbett.mpesasdk.core.models.AccountBalance;
import com.corneliouzbett.mpesasdk.enums.Mode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Initializer {

	public static void main(String [] args) {
		Mpesa.init("46jMLEpzHAnW5bF28zBz7qoF332th2EU", "XANJii8lgyVXg7Dy", Mode.TEST);

		AccountBalance balance = new AccountBalance();
		balance.setInitiator("testapi113");
		balance.setCommandId("AccountBalance");
		balance.setIdentifierType("4");
		balance.setPartyA("+254712884044");
		balance.setResultUrl("");
		balance.setSecurityCredentials("Safaricom007@");

		Mpesa.mpesaService().inquiryAccountBalance(balance).enqueue(new Callback<AccountBalance>() {

			@Override
			public void onResponse(Call<AccountBalance> call, Response<AccountBalance> response) {
				if (response.isSuccessful()) {
				System.out.println("Success" + response.code());
				System.out.println("Success" + response.message());
				System.out.println("Success" + response.body());
				} else {
					try {
						System.out.println("Failed" + response.errorBody().string());
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<AccountBalance> call, Throwable throwable) {
				System.out.println("Error" + call.isExecuted());
			}
		});

	}

}
