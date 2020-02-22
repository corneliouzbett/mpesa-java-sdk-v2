
package com.corneliouzbett.mpesasdk.core.rest.request;

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
public class C2BRequest {

	@Expose
	@SerializedName("CommandID")
	private String CommandId;

	@Expose
	@SerializedName("CommandID")
	private String amount;

	@Expose
	@SerializedName("MSISDN")
	private String msisdn;

	@Expose
	@SerializedName("BillRefNumber")
	private String billRefNumber;

	@Expose
	@SerializedName("ShortCode")
	private String shortCode;
}
