package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.FieldID;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Model {
	private Record currentRecord;

	private Record getCurrentRecord() {
		return (currentRecord == null) ? currentRecord = new Record() : currentRecord;
	}

	public void recordInput(String value, FieldID fieldID) {
		if (value.isEmpty() || fieldID == null) {
			return;
		}

		switch (fieldID) {
			case FIRSTNAME:
				getCurrentRecord().setFirstName(value);
				break;
			case SECONDNAME:
				getCurrentRecord().setSecondName(value);
				break;
			case LOGIN:
				getCurrentRecord().setUserLogin(value);
				break;
		}
	}

	public boolean hasNewRecordInProgress() { return getCurrentRecord().isNew(); }
}
