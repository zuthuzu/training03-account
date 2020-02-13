package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.Model;
import ua.kpi.tef.zu.view.*;

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

	public void startRecordInput() {
		if (fieldReference.getFieldAmount() == 0) {
			view.printAndEndLine(View.RECORD_NO_FIELDS);
			return;
		}

		Scanner sc = new Scanner(System.in);

		//selectLanguage(sc);

		view.printAndEndLine(View.RECORD_INTRO);

		processFields(sc);

		if (model.hasNewRecordInProgress()) {
			view.printAndEndLine(View.RECORD_IN_PROGRESS);
		}
	}

	private void selectLanguage(Scanner sc) {
		view.printAndEndLine(SupportedLanguages.ENGLISH.getUserPrompt());
		view.printAndEndLine(SupportedLanguages.RUSSIAN.getUserPrompt());
	}

	private void processFields(Scanner sc) {
		FieldID[] fieldIDs = fieldReference.getFieldIDs();
		String inputValue;

		for (FieldID fieldID : fieldIDs) {
			inputValue = inputStringValueWithScanner(sc, fieldID);
			if (!inputValue.isEmpty()) {
				model.recordInput(inputValue, fieldID);
			}
		}
	}

	public String inputStringValueWithScanner(Scanner sc, FieldID fieldID) {
		String userPrompt = fieldReference.getInputPromptByFieldID(fieldID);
		String inputValue;

		/*while (!(sc.hasNext() && (inputValue = sc.nextLine()).matches(currentFieldRegex))) {
			view.printAndEndLine(View.WRONG_INPUT);
			view.printAndKeepLine(userPrompt);
		}*/

		boolean valueMeetsRequirements;

		do {
			view.printAndKeepLine(userPrompt);
			view.printAndKeepLine(fieldReference.getFieldOptionalPrompt(fieldID));

			inputValue = sc.nextLine();
			valueMeetsRequirements = isValueOK(inputValue, fieldID);

			if (!valueMeetsRequirements) {
				view.printAndEndLine(fieldReference.getValuePromptByFieldID(fieldID));
			}
		} while (!valueMeetsRequirements);

		return inputValue;
	}

	private boolean isValueOK(String inputValue, FieldID fieldID) {
		if (fieldReference.isFieldOptional(fieldID) && inputValue.isEmpty()) {
			return true;
		} else {
			return inputValue.matches(fieldReference.getRegexByFieldID(fieldID));
		}
	}
}
