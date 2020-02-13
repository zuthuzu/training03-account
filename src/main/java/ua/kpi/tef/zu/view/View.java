package ua.kpi.tef.zu.view;

import ua.kpi.tef.zu.SupportedLanguages;

import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {
	private SupportedLanguages currentLanguage;
	private ResourceBundle bundle;

	public static final String RECORD_INTRO = "record.intro";
	public static final String RECORD_IN_PROGRESS = "record.inprogress";
	public static final String RECORD_NO_FIELDS = "record.nofields";
	public static final String WRONG_INPUT = "input.wrong";
	public static final String FIELD_OPTIONAL = "input.optional";

	public View() {
		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);
	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		bundle = ResourceBundle.getBundle("record", SupportedLanguages.determineLocale(lang));
	}

	public SupportedLanguages getCurrentLanguage() { return currentLanguage; }

	public void printAndKeepLine(String message) { System.out.print(getLocalizedText(message) + " "); }

	public void printAndEndLine(String message) { System.out.println(getLocalizedText(message)); }

	public String getLocalizedText(String property) {
		return bundle.keySet().contains(property) ? bundle.getString(property) : property;
	}
}
