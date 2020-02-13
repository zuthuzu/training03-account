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

	private ArrayList<FieldDescription> fieldDetails = new ArrayList<>();

	public FieldReference() {

		//first three constructor parameters are mandatory, the rest is optional
		fieldDetails.add(new FieldDescription(FieldID.FIRSTNAME, View.INPUT_FIRST_NAME, REGEX_FIRST_NAME, View.FORMAT_NAME));
		fieldDetails.add(new FieldDescription(FieldID.SECONDNAME, View.INPUT_SECOND_NAME, REGEX_SECOND_NAME, View.FORMAT_NAME));
		fieldDetails.add(new FieldDescription(FieldID.PATRONYM, View.INPUT_PATRONYM, REGEX_FIRST_NAME, View.FORMAT_NAME, true));
		fieldDetails.add(new FieldDescription(FieldID.LOGIN, View.INPUT_LOGIN, REGEX_LOGIN, View.FORMAT_LOGIN, false, true));
		fieldDetails.add(new FieldDescription(FieldID.COMMENT, View.INPUT_COMMENT, REGEX_LOGIN, View.FORMAT_LOGIN, true));

	}

	public int getFieldAmount() { return fieldDetails.size(); }

	public FieldDescription[] getFieldDetails() {
		//for some reason fieldDetails.toArray() doesn't work, returs type mismatch: Object[]
		//manual downcasting to (FieldDescription[]) doesn't work either
		//ergo, constructing an array manually for now
		FieldDescription[] result = new FieldDescription[getFieldAmount()];

		for (int i=0; i < getFieldAmount(); i++) {
			result[i] = fieldDetails.get(i);
		}

		return result;
	}

	public String getRegex(FieldDescription field) { return field.getValueRegex(); }

	public String getInputPrompt(FieldDescription field) { return field.getInputPrompt(); }

	public String getValuePrompt(FieldDescription field) {
		String result = field.getValuePrompt();
		return result.isEmpty() ? View.WRONG_INPUT : result;
	}

	public String getFieldOptionalPrompt(FieldDescription field) {
		return isFieldOptional(field) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(FieldDescription field) { return field.isOptional(); }

}
