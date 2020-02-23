package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-24
 */
public class ActiveFieldNotFoundException extends Exception {
	private FieldID fieldID;

	public ActiveFieldNotFoundException(FieldID fieldID) {
		this.fieldID = fieldID;
	}

	public FieldID getFieldID() { return fieldID; }
}
