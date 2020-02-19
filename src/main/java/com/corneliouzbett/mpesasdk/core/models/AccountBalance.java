package com.corneliouzbett.mpesasdk.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountBalance {

	@Expose
	@SerializedName("Initiator")
	private String initiator;

	@Expose
	@SerializedName("SecurityCredential")
	private String SecurityCredentials;

	@Expose
	@SerializedName("CommandID")
	private String CommandId;

	@Expose
	@SerializedName("PartyA")
	private String partyA;

	@Expose
	@SerializedName("IdentifierType")
	private String identifierType;

	@Expose
	@SerializedName("Remarks")
	private String remarks;

	@Expose
	@SerializedName("QueueTimeOutURL")
	private String queueTimeOutURL;

	@Expose
	@SerializedName("ResultURL")
	private String resultUrl;

	public AccountBalance() {
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getSecurityCredentials() {
		return SecurityCredentials;
	}

	public void setSecurityCredentials(String securityCredentials) {
		SecurityCredentials = securityCredentials;
	}

	public String getCommandId() {
		return CommandId;
	}

	public void setCommandId(String commandId) {
		CommandId = commandId;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getQueueTimeOutURL() {
		return queueTimeOutURL;
	}

	public void setQueueTimeOutURL(String queueTimeOutURL) {
		this.queueTimeOutURL = queueTimeOutURL;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}
}
