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
}
