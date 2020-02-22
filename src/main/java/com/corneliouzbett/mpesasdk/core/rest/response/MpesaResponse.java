package com.corneliouzbett.mpesasdk.core.rest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MpesaResponse {

	@Expose
	@SerializedName("OriginatorConversationID")
	private String originatorConversationId;

	@Expose
	@SerializedName("ConversationID")
	private String conversationId;

	@Expose
	@SerializedName("ResponseDescription")
	private String responseDescription;
}
