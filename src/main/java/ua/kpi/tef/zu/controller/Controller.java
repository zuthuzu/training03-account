package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.SupportedLanguages;
import ua.kpi.tef.zu.model.*;
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
		Scanner sc = new Scanner(System.in);

		selectLanguage(sc);

		if (fieldReference.getFieldAmount() == 0) {
			view.printAndEndLine(View.RECORD_NO_FIELDS);
			return;
		}

		view.printAndEndLine(View.RECORD_INTRO);

		processFields(sc);

		if (model.hasNewRecordInProgress()) {
			view.printAndEndLine(View.RECORD_IN_PROGRESS);
		}
	}

	//Language selection methods
	public void selectLanguage(Scanner sc) {
		SupportedLanguages selectedLanguage;

		for (SupportedLanguages option : SupportedLanguages.values()) {
			view.printAndEndLine(option.getUserPrompt());
		}

		selectedLanguage = SupportedLanguages.getSupportedLanguage(languageSelectionLoop(sc));
		sc.nextLine(); //for some reason it's necessary to avoid ghost input further on

		fieldReference.setLocalization(selectedLanguage);
		view.setLocalization(selectedLanguage);
	}

	private int languageSelectionLoop(Scanner sc) {
		int inputValue;

		inputValue = inputIntValueWithScanner(sc);

		while (!Integer.toString(inputValue).matches(SupportedLanguages.LANGUAGE_OPTIONS)) {
			view.printAndEndLine(View.WRONG_INPUT);
			inputValue = inputIntValueWithScanner(sc);
		}

		return inputValue;
	}

	private int inputIntValueWithScanner(Scanner sc) {
		while (!sc.hasNextInt()) {
			view.printAndEndLine(View.WRONG_INPUT);
			sc.next();
		}
		return sc.nextInt();
	}

	//Primary function methods
	private void processFields(Scanner sc) {
		FieldDescription[] fieldDetails = fieldReference.getFieldDetails();
		String inputValue;

		for (FieldDescription field : fieldDetails) {
			model.recordInput(inputFieldValue(sc, field), field.getFieldID());
		}
	}

	private String inputFieldValue(Scanner sc, FieldDescription field) {
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
