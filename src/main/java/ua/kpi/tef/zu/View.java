package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class View {

	public static final String ACCOUNT_INTRO = "Hello! Welcome to personal records storage.";
	public static final String ACCOUNT_NO_FIELDS = "Unfortunately, it seems that storage isn't accepting any data at the moment";
	public static final String WRONG_INPUT = "The data you have entered doesn't match our criteria for this field. Please retry.";
	public static final String INPUT_UNKNOWN = "This field doesn't seem to have a description. Enter something at your discretion: ";
	public static final String INPUT_FIRST_NAME = "What is the subject's first name?";
	public static final String INPUT_SECOND_NAME = "What is the subject's second name?";
	public static final String INPUT_LOGIN = "What is subject's preferred login?";

	public void printAndKeepLine(String message) {
		System.out.print(message + " ");
	}

	public void printAndEndLine(String message) {
		System.out.println(message);
	}
}
