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

	public void writeValueToRecord(String value, FieldID fieldID) {
		if (value.isEmpty() || fieldID == null) {
			return;
		}

		getCurrentRecord();

		switch (fieldID) {
			case FIRSTNAME:
				currentRecord.setFirstName(value);
				break;
			case SECONDNAME:
				currentRecord.setSecondName(value);
				break;
			case PATRONYM:
				currentRecord.setPatronym(value);
				break;
			case LOGIN:
				currentRecord.setLogin(value);
				break;
			case COMMENT:
				currentRecord.setComment(value);
				break;
			case GROUP:
				currentRecord.setGroup(value);
				break;
			case PHONE_LANDLINE:
				currentRecord.setPhoneLandline(value);
				break;
			case PHONE_MOBILE:
				currentRecord.setPhoneMobile(value);
				break;
			case PHONE_MOBILE2:
				currentRecord.setPhoneMobile2(value);
				break;
			case EMAIL:
				currentRecord.setEmail(value);
				break;
			case SKYPE:
				currentRecord.setSkype(value);
				break;
			case ADDRESS_ZIP:
				currentRecord.setAddressZip(value);
				break;
			case ADDRESS_CITY:
				currentRecord.setAddressCity(value);
				break;
			case ADDRESS_STREET:
				currentRecord.setAddressStreet(value);
				break;
			case ADDRESS_BUILDING:
				currentRecord.setAddressBuilding(value);
				break;
			case ADDRESS_APT:
				currentRecord.setAddressApt(value);
				break;
		}

		currentRecord.setChangedDateToCurrent();
	}

	public boolean hasNewRecordInProgress() { return getCurrentRecord().isNew(); }
}
