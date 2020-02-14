package ua.kpi.tef.zu.view;

import ua.kpi.tef.zu.SupportedLanguages;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {
	private SupportedLanguages currentLanguage;
	private ResourceBundle bundle;
	private final String BUNDLE_NAME = "record";

	private DateFormat dateFormat;

	public static final String RECORD_INTRO = "record.intro";
	public static final String RECORD_IN_PROGRESS = "record.inprogress";
	public static final String RECORD_NO_FIELDS = "record.nofields";
	public static final String RECORD_FULL_NAME = "record.fullname";
	public static final String RECORD_FULL_ADDRESS = "record.fulladdress";
	public static final String RECORD_CREATION_DATE = "record.creationdate";
	public static final String RECORD_CHANGE_DATE = "record.changedate";
	public static final String WRONG_INPUT = "input.wrong";
	public static final String FIELD_OPTIONAL = "input.optional";

	public View() {
		currentLanguage = SupportedLanguages.ENGLISH;
		setLocalization(currentLanguage);
	}

	public void setLocalization(SupportedLanguages lang) {
		currentLanguage = lang;
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, SupportedLanguages.determineLocale(lang));
		dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	}

	public SupportedLanguages getCurrentLanguage() { return currentLanguage; }

	public void printAndKeepLine(String message) { System.out.print(getLocalizedText(message) + " "); }

	public void printAndEndLine(String message) { System.out.println(getLocalizedText(message)); }

	public String getLocalizedText(String property) {
		return bundle.keySet().contains(property) ? bundle.getString(property) : property;
	}

	public String getLocalizedDate(Date date) {
		return dateFormat.format(date);
	}
}
