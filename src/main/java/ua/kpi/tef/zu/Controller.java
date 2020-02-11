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
		String[] fieldIDs = fieldReference.getFieldIDs();
		String inputValue;

		for (String fieldID : fieldIDs) {
			inputValue = inputStringValueWithScanner(sc, fieldID);
			if (!inputValue.isEmpty()) {
				model.recordInput(inputValue, fieldID);
			}
		}
	}

	public String inputStringValueWithScanner(Scanner sc, String fieldID) {
		String userPrompt = fieldReference.getIntroByFieldID(fieldID);
		String inputValue;

		/*while (!(sc.hasNext() && (inputValue = sc.nextLine()).matches(currentFieldRegex))) {
			view.printAndEndLine(View.WRONG_INPUT);
			view.printAndKeepLine(userPrompt);
		}*/

		boolean valueMeetsRequirements;

		do {
			view.printAndKeepLine(userPrompt);
			inputValue = sc.nextLine();

			valueMeetsRequirements = isValueOK(inputValue, fieldID);

			if (!valueMeetsRequirements) {
				view.printAndEndLine(View.WRONG_INPUT);
			}
		} while (!valueMeetsRequirements);

		return inputValue;
	}

	private boolean isValueOK(String inputValue, String fieldID) {
		if (fieldReference.isFieldOptional(fieldID) && inputValue.isEmpty()) {
			return true;
		} else {
			return inputValue.matches(fieldReference.getRegexByFieldID(fieldID));
		}
	}
}
