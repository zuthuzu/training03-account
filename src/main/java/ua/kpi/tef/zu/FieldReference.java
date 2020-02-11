package ua.kpi.tef.zu;

import java.util.ArrayList;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldReference {

	private static final String REGEX_FIRST_NAME = "[A-ЯЁ][а-яё]{1,25}";
	private static final String REGEX_SECOND_NAME = "[А-ЯЁ][а-яё]{1,25}([-][А-ЯЁ][а-яё]{1,25})?";
	private static final String REGEX_LOGIN = "[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}";

	private ArrayList<Field> fieldDetails = new ArrayList<>();
	private Field blankField;

	public FieldReference() {

		blankField = new Field();

		fieldDetails.add(new Field("FirstName", View.INPUT_FIRST_NAME, REGEX_FIRST_NAME));
		fieldDetails.add(new Field("SecondName", View.INPUT_SECOND_NAME, REGEX_SECOND_NAME, "", true));
		fieldDetails.add(new Field("Login", View.INPUT_LOGIN, REGEX_LOGIN, View.FORMAT_LOGIN, false, true));

	}

	public int getFieldAmount() {
		return fieldDetails.size();
	}

	public String[] getFieldIDs() {

		String[] result = new String[getFieldAmount()];

		for (int i = 0; i < getFieldAmount(); i++) {
			result[i] = fieldDetails.get(i).getFieldID();
		}

		return result;

	}

	private Field findByID(String fieldID) {
		String lowercaseID = fieldID.toLowerCase();

		for (int i = 0; i < getFieldAmount(); i++) {
			if (fieldDetails.get(i).getFieldID().toLowerCase().equals(lowercaseID)) {
				return fieldDetails.get(i);
			}
		}

		return blankField;
	}

	public String getRegexByFieldID(String fieldID) {
		return findByID(fieldID).getValueRegex();
	}

	public String getInputPromptByFieldID(String fieldID) {
		return findByID(fieldID).getInputPrompt() + getFieldOptionalPrompt(fieldID);
	}

	public String getValuePromptByFieldID(String fieldID) {
		String result = findByID(fieldID).getValuePrompt();
		return result.isEmpty() ? View.WRONG_INPUT : result;
	}

	private String getFieldOptionalPrompt(String fieldID) {
		return isFieldOptional(fieldID) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(String fieldID) {
		return findByID(fieldID).isOptional();
	}

}
