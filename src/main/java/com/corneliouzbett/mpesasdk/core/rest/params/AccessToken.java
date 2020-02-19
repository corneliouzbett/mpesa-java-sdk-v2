package com.corneliouzbett.mpesasdk.core.rest.params;

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
public class AccessToken {

	@Expose
	@SerializedName("access_token")
	private String accessToken;

	@Expose
	@SerializedName("expires_in")
	private String expiresIn;
}
