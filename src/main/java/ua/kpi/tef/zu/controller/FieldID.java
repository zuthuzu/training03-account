package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-11
 */

@SuppressWarnings("SpellCheckingInspection")
public enum FieldID {
	FIRSTNAME("input.firstname", "firstname.regex", "name.description"),
	SECONDNAME("input.secondname", "secondname.regex", "name.description"),
	PATRONYM("input.patronym", "firstname.regex", "name.description"),
	LOGIN("input.login", "login.regex", "login.description"),
	COMMENT("input.comment"),
	GROUP("input.group", RecordGroups.INPUT_OPTIONS, true),
	PHONE_LANDLINE("input.phone.landline", "phone.regex", "phone.description"),
	PHONE_MOBILE("input.phone.mobile", "phone.regex", "phone.description"),
	PHONE_MOBILE2("input.phone.mobile2", "phone.regex", "phone.description"),
	EMAIL("input.email", "email.regex", ""),
	SKYPE("input.skype", "login.regex", "login.description"),
	ADDRESS_ZIP("input.address.zipcode", "zip.regex", "zip.description"),
	ADDRESS_CITY("input.address.city", "secondname.regex", "name.description"),
	ADDRESS_STREET("input.address.street", "streetname.regex", "name.description"),
	ADDRESS_BUILDING("input.address.building", "buildingno.regex", ""),
	ADDRESS_APT("input.address.apt", "number.regex", "number.description");

	private String userPrompt;
	private String regex;
	private String regexDescription;
	private boolean isGroupField = false;

	//filler method for fields still under construction
	FieldID() {}

	FieldID(String userPrompt) {
		this.userPrompt = userPrompt;
		this.regex = ".*"; //if no filter conditions are given, we're matching anything
		this.regexDescription = "";
	}

	FieldID(String userPrompt, String regex, boolean isGroupField) {
		this.userPrompt = userPrompt;
		this.regex = regex;
		this.isGroupField = isGroupField;
		this.regexDescription = "";
	}

	FieldID(String userPrompt, String regex, String regexDescription) {
		this.userPrompt = userPrompt;
		this.regex = regex;
		this.regexDescription = regexDescription;
	}

	public String getUserPrompt() { return userPrompt.isEmpty() ? this.toString() : userPrompt; }

	public String getRegex() { return regex; }

	public String getRegexDescription() { return regexDescription; }

	public boolean isGroupField() { return isGroupField; }
}
