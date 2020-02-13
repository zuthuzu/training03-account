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

	private ArrayList<ActiveField> activeFields = new ArrayList<>();

	public FieldReference() {

		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);

		//first three constructor parameters are mandatory, the rest is optional
		activeFields.add(new ActiveField(FieldID.FIRSTNAME));
		activeFields.add(new ActiveField(FieldID.SECONDNAME));
		activeFields.add(new ActiveField(FieldID.PATRONYM, true));
		activeFields.add(new ActiveField(FieldID.LOGIN, false, true));
		activeFields.add(new ActiveField(FieldID.COMMENT, true));

	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		regexBundle = ResourceBundle.getBundle("regex", SupportedLanguages.determineLocale(lang));
	}

	public String getLocalized(String property) {
		return regexBundle.keySet().contains(property) ? regexBundle.getString(property) : "";
	}

	public int getFieldAmount() { return activeFields.size(); }

	public ActiveField[] getActiveFields() {
		//for some reason fieldDetails.toArray() doesn't work, returs type mismatch: Object[]
		//manual downcasting to (FieldDescription[]) doesn't work either
		//ergo, constructing an array manually for now
		ActiveField[] result = new ActiveField[getFieldAmount()];

		for (int i = 0; i < getFieldAmount(); i++) {
			result[i] = activeFields.get(i);
		}

		return result;
	}

	//these tokens are localized via regexBundle in this class
	public String getRegex(ActiveField field) { return getLocalized(field.getValueRegex()); }

	public String getValuePrompt(ActiveField field) {
		String result = getLocalized(field.getValueDescription());
		return result.isEmpty() ? View.WRONG_INPUT : result;
	}

	//these tokens are localized via record bundle in View, passing them as is
	public String getInputPrompt(ActiveField field) { return field.getInputPrompt(); }

	public String getFieldOptionalPrompt(ActiveField field) {
		return isFieldOptional(field) ? View.FIELD_OPTIONAL : "";
	}

	public boolean isFieldOptional(ActiveField field) { return field.isOptional(); }

}
