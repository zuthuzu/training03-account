package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldReference {

	private static final String REGEX_FIRST_NAME = "[A-ЯЁ][а-яё]{1,25}";
	private static final String REGEX_SECOND_NAME = "[А-ЯЁ][а-яё]{1,25}([-][А-ЯЁ][а-яё]{1,25})?";
	private static final String REGEX_LOGIN = "[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}";

	private ArrayList<FieldDetails> fieldDetails = new ArrayList<>();
	private FieldDetails blankField;

	public FieldReference() {

		blankField = new FieldDetails();

		//first three constructor parameters are mandatory, the rest is optional
		fieldDetails.add(new FieldDetails(FieldID.FIRSTNAME, View.INPUT_FIRST_NAME, REGEX_FIRST_NAME, View.FORMAT_NAME));
		fieldDetails.add(new FieldDetails(FieldID.SECONDNAME, View.INPUT_SECOND_NAME, REGEX_SECOND_NAME, View.FORMAT_NAME));
		fieldDetails.add(new FieldDetails(FieldID.PATRONYM, View.INPUT_PATRONYM, REGEX_FIRST_NAME, View.FORMAT_NAME, true));
		fieldDetails.add(new FieldDetails(FieldID.LOGIN, View.INPUT_LOGIN, REGEX_LOGIN, View.FORMAT_LOGIN, false, true));
		fieldDetails.add(new FieldDetails(FieldID.COMMENT, View.INPUT_COMMENT, REGEX_LOGIN, View.FORMAT_LOGIN, true));

	}

	public int getFieldAmount() { return fieldDetails.size(); }

	public FieldID[] getFieldIDs() {

		FieldID[] result = new FieldID[getFieldAmount()];

		for (int i = 0; i < getFieldAmount(); i++) {
			result[i] = fieldDetails.get(i).getFieldID();
		}

		return result;
	}

	private FieldDetails findByID(FieldID fieldID) {

		for (int i = 0; i < getFieldAmount(); i++) {
			if (fieldDetails.get(i).getFieldID() == fieldID) {
				return fieldDetails.get(i);
			}
		}

		return blankField;
	}

	public String getRegexByFieldID(FieldID fieldID) {
		return findByID(fieldID).getValueRegex();
	}

	public String getInputPromptByFieldID(FieldID fieldID) {
		return findByID(fieldID).getInputPrompt();
	}

	public String getValuePromptByFieldID(FieldID fieldID) {
		String result = findByID(fieldID).getValuePrompt();
		return result.isEmpty() ? View.WRONG_INPUT : result;
	}

	public String getFieldOptionalPrompt(FieldID fieldID) {
		return isFieldOptional(fieldID) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(FieldID fieldID) {
		return findByID(fieldID).isOptional();
	}

}
