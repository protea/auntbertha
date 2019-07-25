package cc.protea.auntbertha;

import java.util.List;

import cc.protea.auntbertha.search.AuntBerthaProgram;
import cc.protea.auntbertha.search.AuntBerthaSearchProgramListResponse;

public class AuntBerthaSearch {
	
	final AuntBertha ab;
	final String urlbase;
	
	public AuntBerthaSearch(AuntBertha ab) {
		this.ab = ab;
		this.urlbase = ab.urlBase + "search_v2/v2/";
	}
	
	public List<AuntBerthaProgram> getByServiceTag(String zipcode, String serviceTag) {
		String url = urlbase + 
				"zipcodes/" + AuntBerthaUtils.encode(AuntBerthaUtils.trim(zipcode)) + 
				"/programs";
		url = AuntBerthaUtils.addParameter(url, "serviceTag", AuntBerthaUtils.trim(serviceTag));
		url = AuntBerthaUtils.addParameter(url, "cookie", "protea");
		AuntBerthaSearchProgramListResponse response = ab.utils.get(ab.apiKey, url, AuntBerthaSearchProgramListResponse.class);
		return response.programs;
	}
	
	public List<AuntBerthaProgram> getBySearchTerm(String zipcode, String searchTerm) {
		String url = urlbase + 
				"zipcodes/" + AuntBerthaUtils.encode(AuntBerthaUtils.trim(zipcode)) + 
				"/programs";
		url = AuntBerthaUtils.addParameter(url, "terms", AuntBerthaUtils.trim(searchTerm));
		url = AuntBerthaUtils.addParameter(url, "cookie", "protea");
		AuntBerthaSearchProgramListResponse response = ab.utils.get(ab.apiKey, url, AuntBerthaSearchProgramListResponse.class);
		return response.programs;
	}
}
