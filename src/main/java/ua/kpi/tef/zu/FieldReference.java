package ua.kpi.tef.zu;

import java.util.TreeSet;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldReference {

	public static final String REGEX_FIRST_NAME = "[A-ЯЁ][а-яё]{1,25}";
	public static final String REGEX_SECOND_NAME = "[А-ЯЁ][а-яё]{1,25}([-][А-ЯЁ][а-яё]*)?";
	public static final String REGEX_LOGIN = "[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}";

	private static String[] fieldList = new String[]{"FirstName", "Login"}; //abridged version for now
	//public static String[] fieldList = new String[]{"FirstName", "SecondName", "Login"};

	private TreeSet<String> requiredFields = new TreeSet<>();

	public FieldReference() {
		setRequiredFields();
	}

	private void setRequiredFields() {
		requiredFields.add("FirstName");
		requiredFields.add("Login");
	}

	public int getFieldAmount() {
		return fieldList.length;
	}

	public String[] getFieldList() {
		return fieldList;
	}

	public String getRegexByFieldID(String fieldID) {
		String lowercaseID = fieldID.toLowerCase();

		switch (lowercaseID) {
			case "firstname":
				return REGEX_FIRST_NAME;
			case "secondname":
				return REGEX_SECOND_NAME;
			case "login":
				return REGEX_LOGIN;
			default:
				return null;
		}
	}

	public String getIntroByFieldID(String fieldID) {
		String lowercaseID = fieldID.toLowerCase();

		switch (lowercaseID) {
			case "firstname":
				return View.INPUT_FIRST_NAME;
			case "secondname":
				return View.INPUT_SECOND_NAME;
			case "login":
				return View.INPUT_LOGIN;
			default:
				return View.INPUT_UNKNOWN; //this should never happen
		}
	}

	public boolean isFieldRequired(String fieldID) {
		return requiredFields.contains(fieldID);
	}
}
