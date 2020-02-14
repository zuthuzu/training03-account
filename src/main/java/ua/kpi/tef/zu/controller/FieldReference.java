package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.view.View;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 * <br><br>
 * Contains the interface for working with record storage.
 * Primarily, contains the list of record fields that are currently accepting data,
 * as well as their properties (user prompts, data filtering, user prompts, optionality, etc)
 * <br><br>
 * Accepts (and requires) localization from 'regex' bundle, which contains regular expressions for filtering user data
 * and user-readable descriptions of those regexes.
 */

public class FieldReference {

	private SupportedLanguages currentLanguage;
	private ResourceBundle regexBundle;
	private final String BUNDLE_NAME = "regex";

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
		activeFields.add(new ActiveField(FieldID.GROUP));
		activeFields.add(new ActiveField(FieldID.PHONE_MOBILE));
		activeFields.add(new ActiveField(FieldID.PHONE_LANDLINE, true));
		activeFields.add(new ActiveField(FieldID.PHONE_MOBILE2, true));
		activeFields.add(new ActiveField(FieldID.EMAIL));
		activeFields.add(new ActiveField(FieldID.SKYPE, true));
		activeFields.add(new ActiveField(FieldID.ADDRESS_ZIP, true));
		activeFields.add(new ActiveField(FieldID.ADDRESS_CITY));
		activeFields.add(new ActiveField(FieldID.ADDRESS_STREET));
		activeFields.add(new ActiveField(FieldID.ADDRESS_BUILDING));
		activeFields.add(new ActiveField(FieldID.ADDRESS_APT));

	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		regexBundle = ResourceBundle.getBundle(BUNDLE_NAME, SupportedLanguages.determineLocale(lang));
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
	public String getLocalizedRegex(ActiveField field) { return getLocalized(field.getValueRegex()); }

	public String getLocalizedDescription(ActiveField field) {
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
