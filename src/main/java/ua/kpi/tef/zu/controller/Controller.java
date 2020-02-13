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
		FieldDescription[] fieldDetails = fieldReference.getFieldDetails();
		String inputValue;

		for (FieldDescription field : fieldDetails) {
			inputValue = inputStringValueWithScanner(sc, field);
			if (!inputValue.isEmpty()) {
				model.recordInput(inputValue, field.getFieldID());
			}
		}
	}

	public String inputStringValueWithScanner(Scanner sc, FieldDescription field) {
		String userPrompt = fieldReference.getInputPrompt(field);
		String inputValue;

		/*while (!(sc.hasNext() && (inputValue = sc.nextLine()).matches(currentFieldRegex))) {
			view.printAndEndLine(View.WRONG_INPUT);
			view.printAndKeepLine(userPrompt);
		}*/

		boolean valueMeetsRequirements;

		do {
			view.printAndKeepLine(userPrompt);
			view.printAndKeepLine(fieldReference.getFieldOptionalPrompt(field));

			inputValue = sc.nextLine();
			valueMeetsRequirements = isValueOK(inputValue, field);

			if (!valueMeetsRequirements) {
				view.printAndEndLine(fieldReference.getValuePrompt(field));
			}
		} while (!valueMeetsRequirements);

		return inputValue;
	}

	private boolean isValueOK(String inputValue, FieldDescription field) {
		if (fieldReference.isFieldOptional(field) && inputValue.isEmpty()) {
			return true;
		} else {
			return inputValue.matches(fieldReference.getRegex(field));
		}
	}
}
