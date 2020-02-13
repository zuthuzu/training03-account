package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldReference {

	private SupportedLanguages currentLanguage;
	private ResourceBundle regexBundle;

	private ArrayList<FieldDescription> fieldDetails = new ArrayList<>();

	public FieldReference() {

		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);

		//first three constructor parameters are mandatory, the rest is optional
		fieldDetails.add(new FieldDescription(FieldID.FIRSTNAME));
		fieldDetails.add(new FieldDescription(FieldID.SECONDNAME));
		fieldDetails.add(new FieldDescription(FieldID.PATRONYM, true));
		fieldDetails.add(new FieldDescription(FieldID.LOGIN, false, true));
		fieldDetails.add(new FieldDescription(FieldID.COMMENT, true));

	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		regexBundle = ResourceBundle.getBundle("regex", SupportedLanguages.determineLocale(lang));
	}

	public String getLocalized(String property) {
		return regexBundle.keySet().contains(property) ? regexBundle.getString(property) : "";
	}

	public int getFieldAmount() { return fieldDetails.size(); }

	public FieldDescription[] getFieldDetails() {
		//for some reason fieldDetails.toArray() doesn't work, returs type mismatch: Object[]
		//manual downcasting to (FieldDescription[]) doesn't work either
		//ergo, constructing an array manually for now
		FieldDescription[] result = new FieldDescription[getFieldAmount()];

		for (int i = 0; i < getFieldAmount(); i++) {
			result[i] = fieldDetails.get(i);
		}

		return result;
	}

	//these tokens are localized via regexBundle in this class
	public String getRegex(FieldDescription field) { return getLocalized(field.getValueRegex()); }

	public String getValuePrompt(FieldDescription field) {
		String result = getLocalized(field.getValueDescription());
		return result.isEmpty() ? View.WRONG_INPUT : result;
	}

	//these tokens are localized via record bundle in View, passing them as is
	public String getInputPrompt(FieldDescription field) { return field.getInputPrompt(); }

	public String getFieldOptionalPrompt(FieldDescription field) {
		return isFieldOptional(field) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(FieldDescription field) { return field.isOptional(); }

}
