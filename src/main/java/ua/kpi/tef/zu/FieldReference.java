package ua.kpi.tef.zu;

import java.util.TreeSet;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldReference {

	private static final String REGEX_FIRST_NAME = "[A-ЯЁ][а-яё]{1,25}";
	private static final String REGEX_SECOND_NAME = "[А-ЯЁ][а-яё]{1,25}([-][А-ЯЁ][а-яё]*)?";
	private static final String REGEX_LOGIN = "[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}";

	private static String[] fieldList = new String[]{"FirstName", "Login"}; //abridged version for now
	//public static String[] fieldList = new String[]{"FirstName", "SecondName", "Login"};

	private TreeSet<String> optionalFields = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

	public FieldReference() {
		setOptionalFields();
	}

	private void setOptionalFields() {
		optionalFields.add("Login");
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
		StringBuilder result = new StringBuilder();

		switch (lowercaseID) {
			case "firstname":
				result.append(View.INPUT_FIRST_NAME);
				break;
			case "secondname":
				result.append(View.INPUT_SECOND_NAME);
				break;
			case "login":
				result.append(View.INPUT_LOGIN);
				break;
			default:
				result.append(View.INPUT_UNKNOWN); //this should never happen
				break;
		}

		result.append(getFieldOptionalPrompt(fieldID));

		return result.toString();
	}

	private String getFieldOptionalPrompt(String fieldID) {
		return isFieldOptional(fieldID) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(String fieldID) {
		return optionalFields.contains(fieldID);
	}
}
