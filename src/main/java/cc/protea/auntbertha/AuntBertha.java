package cc.protea.auntbertha;

public class AuntBertha {
	
	static String urlbase = "https://ab-giving-dot-searchbertha-qa1.appspot.com/_ah/api/";

	final String apiKey;

	final AuntBerthaUtils utils = new AuntBerthaUtils();

	public AuntBertha(String apiKey) {
		this.apiKey = apiKey;
		this.gives = new AuntBerthaGives(this);
		
	}

	public final AuntBerthaGives gives;

}
