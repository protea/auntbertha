package cc.protea.auntbertha.search;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuntBerthaSearchProgramListResponse {

	public static class TagCount {
		public static class Count {
			public int count;
			public String name;
		}
		public String name;
		public List<Count> children = new ArrayList<>();
	}
	
	@JsonProperty("attribute_tag_counts") public List<TagCount> attributeTagCounts = new ArrayList<>();
	public int count;
	@JsonProperty("postal_location") public AuntBerthaProgram.Location postalLocation;
	public List<AuntBerthaProgram> programs = new ArrayList<>();
	
}
