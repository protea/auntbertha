package cc.protea.auntbertha;

import java.util.List;

import cc.protea.auntbertha.gives.AuntBerthaGivesSeekerRequest;
import cc.protea.auntbertha.gives.AuntBerthaGivesSeekerRequestListResponse;

public class AuntBerthaGives {
	
	final AuntBertha ab;
	final String urlbase;
	
	public AuntBerthaGives(AuntBertha ab) {
		this.ab = ab;
		this.urlbase = AuntBertha.urlbase + "ab_gives/v1/";
	}
	
	public List<AuntBerthaGivesSeekerRequest> getAllSeekerRequests() {
		return findSeekerRequestsByProgram("all");
	}

	
	public List<AuntBerthaGivesSeekerRequest> findSeekerRequestsByProgram(String programId) {
		String url = urlbase + "seekerRequest/findByProgramId?programId=all&cookie=ab_gives";
		AuntBerthaGivesSeekerRequestListResponse response = ab.utils.get(ab.apiKey, url, AuntBerthaGivesSeekerRequestListResponse.class);
		return response.requests;
	}
	
	public AuntBerthaResponse fulfill(String seekerRequestId) {
		String url = urlbase + "seekerRequest/" + AuntBerthaUtils.trim(seekerRequestId) + "?programId=all&cookie=ab_gives&status=paid";
		AuntBerthaResponse response = ab.utils.put(ab.apiKey, url, "", AuntBerthaResponse.class);
		if (response.responseCode >= 200 && response.responseCode < 299) {
			response.success = true;
		}
		if (response.responseCode == 409) {
			response.success = false;
			response.errorMessage = "Request already fulfilled";
		}
		return response;
	}

}
