package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {

	public static final String ACCOUNT_INTRO = "Hello! Welcome to the personal records storage.";
	public static final String RECORD_IN_PROGRESS = "The data you've entered so far has been accepted into storage.";
	public static final String ACCOUNT_NO_FIELDS = "Unfortunately, it seems that storage isn't accepting any data at the moment";
	public static final String WRONG_INPUT = "The data you have entered doesn't match our criteria for this field.";
	public static final String FIELD_OPTIONAL = " (this field is optional, you may skip it) ";
	public static final String INPUT_FIRST_NAME = "What is the subject's first name?";
	public static final String INPUT_SECOND_NAME = "What is the subject's second name?";
	public static final String INPUT_LOGIN = "What is the subject's preferred login?";
	public static final String FORMAT_LOGIN = "Login can contain latin characters, numbers, dot, dash, underscore. Can't start with the number.";

	public void printAndKeepLine(String message) {
		System.out.print(message + " ");
	}

	public void printAndEndLine(String message) {
		System.out.println(message);
	}
}
