package ua.kpi.tef.zu;

import java.util.Scanner;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Controller {
	// Constructor
	private Model model;
	private View view;
	private FieldReference fieldReference;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		fieldReference = new FieldReference();
	}

	public void startAccountInput() {
		if (fieldReference.getFieldAmount() == 0) {
			view.printAndEndLine(View.ACCOUNT_NO_FIELDS);
			return;
		}

		Scanner sc = new Scanner(System.in);

		view.printAndEndLine(View.ACCOUNT_INTRO);

		processFields(sc);

	}

	private void processFields(Scanner sc) {
		String[] fieldList = fieldReference.getFieldList();

		for (String fieldID : fieldList) {
			model.recordInput(inputStringValueWithScanner(sc, fieldID), fieldID);
		}
	}

	// The Utility methods
	public String inputStringValueWithScanner(Scanner sc, String fieldID) {
		String userPrompt = fieldReference.getIntroByFieldID(fieldID);
		String currentFieldRegex = fieldReference.getRegexByFieldID(fieldID);
		String inputValue;

		if (currentFieldRegex == null) {
			return "";
		}

		view.printAndKeepLine(userPrompt);
		while (!(sc.hasNext() && (inputValue = sc.nextLine()).matches(currentFieldRegex))) {
			view.printAndEndLine(View.WRONG_INPUT);
			view.printAndKeepLine(userPrompt);
		}

		return inputValue;
	}
}
