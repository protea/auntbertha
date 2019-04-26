package cc.protea.auntbertha.gives;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuntBerthaGivesSeekerRequest {
	public Date dateCreated;
	public String details;
	public String programId;
	public String referralId;
	public String requestType;
	@JsonProperty("seeker_request_id") public String seekerRequestId;
	public String state;
	public String zipcode;
	public String neighborhood;
}