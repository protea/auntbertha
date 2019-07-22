package cc.protea.auntbertha;

public class AuntBertha {
	
	String urlBase = "https://www.auntbertha.com/_ah/api/";

	final String apiKey;

	final AuntBerthaUtils utils = new AuntBerthaUtils();

	public AuntBertha(String apiKey) {
		this.apiKey = apiKey;
		this.gives = new AuntBerthaGives(this);
		this.search = new AuntBerthaSearch(this);
	}

	public AuntBertha(String apiKey, String urlBase) {
		this.apiKey = apiKey;
		if (urlBase != null && urlBase.trim().length() > 0) {
			this.urlBase = urlBase;
		}
		this.gives = new AuntBerthaGives(this);	
		this.search = new AuntBerthaSearch(this);
	}

	public final AuntBerthaGives gives;
	public final AuntBerthaSearch search;

}
