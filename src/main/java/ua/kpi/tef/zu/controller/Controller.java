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

	//Localization tech
	public void selectLanguage(Scanner sc) {
		SupportedLanguages selectedLanguage;

		for (SupportedLanguages option : SupportedLanguages.values()) {
			view.printAndEndLine(option.getUserPrompt());
		}

		selectedLanguage = SupportedLanguages.getSupportedLanguage(groupSelectionLoop(sc, SupportedLanguages.INPUT_OPTIONS));

		fieldReference.setLocalization(selectedLanguage);
		view.setLocalization(selectedLanguage);
	}

	//Primary function methods
	private void processFields(Scanner sc) {
		ActiveField[] activeFields = fieldReference.getActiveFields();
		String inputValue;

		for (ActiveField field : activeFields) {
			model.recordInput(inputFieldValue(sc, field), field.getFieldID());
		}
	}

	private String inputFieldValue(Scanner sc, ActiveField field) {
		if (field.isGroupField()) {
			return inputGroupField(sc, field);
		} else {
			return inputStringField(sc, field);
		}
	}

	private String inputStringField(Scanner sc, ActiveField field) {
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
				view.printAndEndLine(fieldReference.getLocalizedDescription(field));
			}
		} while (!valueMeetsRequirements);

		return inputValue;
	}

	private boolean isValueOK(String inputValue, ActiveField field) {
		if (fieldReference.isFieldOptional(field) && inputValue.isEmpty()) {
			return true;
		} else {
			return inputValue.matches(fieldReference.getLocalizedRegex(field));
		}
	}

	private String inputGroupField(Scanner sc, ActiveField field) {
		view.printAndEndLine(fieldReference.getInputPrompt(field));

		//hardcoding for now, don't know how to do it right yet
		//ideally it'd store field type somewhere and implement its' specific foreach, getInputKey and getNameByKey
		if (field.getFieldID() == FieldID.GROUP) {
			for (RecordGroups option : RecordGroups.values()) {
				view.printAndKeepLine(option.getInputKey() + ":");
				view.printAndEndLine(option.getDisplayName());
			}

			return RecordGroups.getNameByKey(groupSelectionLoop(sc, field.getValueRegex()));
		}

		return "";
	}


	private int groupSelectionLoop(Scanner sc, String regexAllowed) {
		int inputValue;

		inputValue = inputIntValueWithScanner(sc);

		while (!Integer.toString(inputValue).matches(regexAllowed)) {
			view.printAndEndLine(View.WRONG_INPUT);
			inputValue = inputIntValueWithScanner(sc);
		}

		sc.nextLine(); //to avoid ghost input further on

		return inputValue;
	}

	private int inputIntValueWithScanner(Scanner sc) {
		while (!sc.hasNextInt()) {
			view.printAndEndLine(View.WRONG_INPUT);
			sc.next();
		}
		return sc.nextInt();
	}
}
