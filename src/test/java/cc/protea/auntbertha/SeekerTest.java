package cc.protea.auntbertha;

import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import cc.protea.auntbertha.gives.AuntBerthaGivesSeekerRequest;
import cc.protea.auntbertha.search.AuntBerthaProgram;

public class SeekerTest {

	private AuntBertha auntBertha;

	@Before
	public void before() {
		auntBertha = getAuntBertha();
		Assume.assumeNotNull(auntBertha);
	}

	@Test
	public void testSeekerList() throws Exception {
		List<AuntBerthaGivesSeekerRequest> requests = auntBertha.gives.getAllSeekerRequests();
		Assert.assertNotNull(requests);
		System.out.println(AuntBerthaJsonUtil.toJson(requests));
	}

	@Test
	public void testProgramList() throws Exception {
		List<AuntBerthaProgram> programs = auntBertha.search.getByServiceTag("78701", "food pantry");
		Assert.assertNotNull(programs);
		System.out.println(AuntBerthaJsonUtil.toJson(programs));
	}
	
	private static AuntBertha getAuntBertha() {
		final String apiKey = System.getenv("AUNTBERTHA_API_KEY");
		if (apiKey == null) {
			return null;
		}
		System.out.println("Aunt Bertha unit tests running with API key " + apiKey);
		return new AuntBertha(apiKey);
	}

}
