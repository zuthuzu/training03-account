package ua.kpi.tef.zu;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {
	private ResourceBundle bundle;
	//PrintStream out = new PrintStream(System.out, true, "UTF-8");

	public static final String RECORD_INTRO = "record.intro";
	public static final String RECORD_IN_PROGRESS = "record.inprogress";
	public static final String RECORD_NO_FIELDS = "record.nofields";
	public static final String WRONG_INPUT = "input.wrong";
	public static final String FIELD_OPTIONAL = "input.optional";
	public static final String INPUT_FIRST_NAME = "input.firstname";
	public static final String INPUT_SECOND_NAME = "input.secondname";
	public static final String FORMAT_NAME = "input.name.value";
	public static final String INPUT_LOGIN = "input.login";
	public static final String FORMAT_LOGIN = "input.login.value";

	public View() {
		Locale currentLocale = new Locale("en", "US");
		//Locale currentLocale = new Locale("ru","RU");

		bundle = ResourceBundle.getBundle("record", currentLocale);
	}

	public void printAndKeepLine(String message) {
		System.out.print(getLocalizedText(message) + " ");
	}

	public void printAndEndLine(String message) {
		System.out.println(getLocalizedText(message));
	}

	public String getLocalizedText(String property) {
		return bundle.keySet().contains(property) ? bundle.getString(property) : property;
	}
}
