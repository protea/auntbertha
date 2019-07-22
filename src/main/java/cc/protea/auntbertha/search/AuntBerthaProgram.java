package cc.protea.auntbertha.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuntBerthaProgram {
	
	public static class CentralHours {
		public boolean open;
		@JsonProperty("open_all_day") public boolean openAllDay;
		@JsonProperty("open_time") public String openTime;
		@JsonProperty("close_time") public String closeTime;
	}
	
	public static class Hours { 
		public boolean monday;
		@JsonProperty("monday_all_day") public boolean mondayAllDay;
		@JsonProperty("monday_start") public String mondayStart;
		@JsonProperty("monday_finish") public String mondayFinish;
		public boolean tuesday;
		@JsonProperty("tuesday_all_day") public boolean tuesdayAllDay;
		@JsonProperty("tuesday_start") public String tuesdayStart;
		@JsonProperty("tuesday_finish") public String tuesdayFinish;
		public boolean wednesday;
		@JsonProperty("wednesday_all_day") public boolean wednesdayAllDay;
		@JsonProperty("wednesday_start") public String wednesdayStart;
		@JsonProperty("wednesday_finish") public String wednesdayFinish;
		public boolean thursday;
		@JsonProperty("thursday_all_day") public boolean thursdayAllDay;
		@JsonProperty("thursday_start") public String thursdayStart;
		@JsonProperty("thursday_finish") public String thursdayFinish;
		public boolean friday;
		@JsonProperty("friday_all_day") public boolean fridayAllDay;
		@JsonProperty("friday_start") public String fridayStart;
		@JsonProperty("friday_finish") public String fridayFinish;
		public boolean saturday;
		@JsonProperty("saturday_all_day") public boolean saturdayAllDay;
		@JsonProperty("saturday_start") public String saturdayStart;
		@JsonProperty("saturday_finish") public String saturdayFinish;
		public boolean sunday;
		@JsonProperty("sunday_all_day") public boolean sundayAllDay;
		@JsonProperty("sunday_start") public String sundayStart;
		@JsonProperty("sunday_finish") public String sundayFinish;
	}
	
	public static class Step {
		public String action;
		public String channel;
		public String contact;
	}
	
	public static class Location {
		public Double latitude;
		public Double longitude;
	}
	
	public static class OpenNowInfo {
		@JsonProperty("close_time") public String closeTime;
		@JsonProperty("day_of_the_week") public String dayOfTheWeek;
		@JsonProperty("open_all_day") public boolean openAllDay;
		@JsonProperty("open_now") public boolean openNow;
		@JsonProperty("open_time") public String openTime;
	}
	
	public static class Office {
		public String name;
		@JsonProperty("phone_number") public String phoneNumber;
		@JsonProperty("website_url") public String websiteUrl;
		public String address1;
		public String address2;
		public String city;
		public Double distance;
		public String email;
		@JsonProperty("fax_number") public String faxNumber;
		public Hours hours;
		@JsonProperty("is_administrative") public boolean administrative;
		public Location location;
		public String notes;
		@JsonProperty("office_numeric_id") public String officeNumericId;
		@JsonProperty("office_type") public List<String> officeType;
		@JsonProperty("open_now_info") public OpenNowInfo openNowInfo;
		public String postal;
		public String state;
		@JsonProperty("supported_languages") public List<String> supportedLanguages;
		@JsonProperty("url_safe_key") public String urlSafeKey;
	}
	
	public static class WeekHours { 
		public CentralHours monday;
		public CentralHours tuesday;
		public CentralHours wednesday;
		public CentralHours thursday;
		public CentralHours friday;
		public CentralHours saturday;
		public CentralHours sunday;
	}
	
	public String id;
	public String name;
	public String description;
	@JsonProperty("attribute_tags") public List<String> attributeTags;
	@JsonProperty("service_tags") public List<String> serviceTags;
	@JsonProperty("video_url") public String videoUrl;
	@JsonProperty("website_url") public String websiteUrl;
	@JsonProperty("facebook_url") public String facebookUrl;
	@JsonProperty("twitter_id") public String twitterId;
	public String availability;
	@JsonProperty("central_hours_of_operation") public WeekHours centralHoursOfOperation;
	@JsonProperty("coverage_description") public String coverageDescription;
	@JsonProperty("free_or_reduced") public String freeOrReduced;
	@JsonProperty("is_office_available") public boolean isOfficeAvailable;
	public String directions;
	@JsonProperty("next_steps") public List<Step> nextSteps;
	public String channel;
	public String action;
	public String grain;
	@JsonProperty("grain_location") public List<String> grainLocation;
	public List<Office> offices;
	@JsonProperty("program_numeric_id") public String programNumericId;
	@JsonProperty("provider_name") public String providerName;
	@JsonProperty("provider_numeric_id") public String providerNumericId;
	public List<String> rules;
	public double score;
	@JsonProperty("supported_languages") public List<String> supportedLanguages;
	@JsonProperty("update_date") public String updateDate;
	@JsonProperty("validation_date") public String validationDate;
	@JsonProperty("wl_score") public double wlScore;
}
