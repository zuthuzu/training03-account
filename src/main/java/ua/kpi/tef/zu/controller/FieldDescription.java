package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class FieldDescription {
	private FieldID fieldID;
	private String inputPrompt;
	private String valueRegex;
	private String valuePrompt = "";
	private boolean isOptional = false;
	private boolean isUnique = false;

	//overloading for different possible amount of details at init
	public FieldDescription() {
		//a blank field that isn't included in normal reference, serves as a null substitute
		this.fieldID = null;
		this.inputPrompt = "";
		this.valueRegex = "";
	}

	public FieldDescription(FieldID fieldID, String inputPrompt, String valueRegex) {
		this.fieldID = fieldID;
		this.inputPrompt = inputPrompt;
		this.valueRegex = valueRegex;
	}

	public FieldDescription(FieldID fieldID, String inputPrompt, String valueRegex, String valuePrompt) {
		this.fieldID = fieldID;
		this.inputPrompt = inputPrompt;
		this.valueRegex = valueRegex;
		this.valuePrompt = valuePrompt;
	}

	public FieldDescription(FieldID fieldID, String inputPrompt, String valueRegex, String valuePrompt, boolean isOptional) {
		this.fieldID = fieldID;
		this.inputPrompt = inputPrompt;
		this.valueRegex = valueRegex;
		this.valuePrompt = valuePrompt;
		this.isOptional = isOptional;
	}

	public FieldDescription(FieldID fieldID, String inputPrompt, String valueRegex, String valuePrompt, boolean isOptional, boolean isUnique) {
		this.fieldID = fieldID;
		this.inputPrompt = inputPrompt;
		this.valueRegex = valueRegex;
		this.valuePrompt = valuePrompt;
		this.isOptional = isOptional;
		this.isUnique = isUnique;
	}

	public FieldID getFieldID() { return fieldID; }

	public String getInputPrompt() { return inputPrompt; }

	public String getValueRegex() { return valueRegex; }

	public String getValuePrompt() { return valuePrompt; }

	public boolean isOptional() { return isOptional; }

	public boolean isUnique() { return isUnique; }
}
