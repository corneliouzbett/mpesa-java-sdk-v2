package com.corneliouzbett.mpesasdk.core.rest.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BusinessUri {

	@Expose
	@SerializedName("ValidationURL")
	private String validationUrl;

	@Expose
	@SerializedName("ConfirmationURL")
	private String confirmationUrl;

	@Expose
	@SerializedName("ResponseType")
	private String responseType;

	@Expose
	@SerializedName("ShortCode")
	private int shortCode;

}
