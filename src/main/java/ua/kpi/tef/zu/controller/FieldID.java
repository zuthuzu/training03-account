package ua.kpi.tef.zu.controller;

@SuppressWarnings("SpellCheckingInspection")
public enum FieldID {
	FIRSTNAME("input.firstname", "firstname.regex", "name.description"),
	SECONDNAME("input.secondname", "secondname.regex", "name.description"),
	PATRONYM("input.patronym", "firstname.regex", "name.description"),
	LOGIN("input.login", "login.regex", "login.description"),
	COMMENT("input.comment"),
	GROUP,
	PHONE_LANDLINE,
	PHONE_MOBILE,
	PHONE_MOBILE2,
	EMAIL,
	SKYPE,
	ADDRESS_ZIP,
	ADDRESS_CITY,
	ADDRESS_STREET,
	ADDRESS_BUILDING,
	ADDRESS_APT;

	private String userPrompt;
	private String regex;
	private String regexDescription;

	FieldID() {}

	FieldID(String userPrompt) {
		this.userPrompt = userPrompt;
		this.regex = ".*"; //if no filter conditions are given, we're matching anything
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
}
