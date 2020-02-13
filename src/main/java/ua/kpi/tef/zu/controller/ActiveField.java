package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class ActiveField {
	private FieldID fieldID;
	private boolean isOptional = false;
	private boolean isUnique = false;

	public ActiveField(FieldID fieldID) {
		this.fieldID = fieldID;
	}

	public ActiveField(FieldID fieldID, boolean isOptional) {
		this.fieldID = fieldID;
		this.isOptional = isOptional;
	}

	public ActiveField(FieldID fieldID, boolean isOptional, boolean isUnique) {
		this.fieldID = fieldID;
		this.isOptional = isOptional;
		this.isUnique = isUnique;
	}

	public FieldID getFieldID() { return fieldID; }

	public String getInputPrompt() { return fieldID.getUserPrompt(); }

	public String getValueRegex() { return fieldID.getRegex(); }

	public String getValueDescription() { return fieldID.getRegexDescription(); }

	public boolean isGroupField () { return fieldID.isGroupField(); }

	public boolean isOptional() { return isOptional; }

	public boolean isUnique() { return isUnique; }
}
