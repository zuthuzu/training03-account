package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.FieldID;

/**
 * Created by Anton Domin on 2020-02-23
 */
public class PersistanceException extends Exception {
	private Record record;
	private String field;
	private FieldID fieldID;

	public PersistanceException(Record record, String field) {
		this.record = record;
		this.field = field;

		switch (field) {
			case "login":
				this.fieldID = FieldID.LOGIN;
				break;
			case "email":
				this.fieldID = FieldID.EMAIL;
				break;
		}
	}

	public PersistanceException(Record record, Exception e) {
		super(e);
		this.record = record;
	}

	public Record getRecord() { return record; }

	public String getFieldString() { return field; }

	public FieldID getFieldID() { return fieldID; }
}
