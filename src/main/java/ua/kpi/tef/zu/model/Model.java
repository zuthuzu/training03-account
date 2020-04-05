package ua.kpi.tef.zu.model;

import ua.kpi.tef.zu.controller.FieldID;
import ua.kpi.tef.zu.model.dao.DaoFactory;
import ua.kpi.tef.zu.model.dao.RecordDao;

import java.time.LocalDate;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Model {
	private Record currentRecord;

	private Record getCurrentRecord() {
		return (currentRecord == null) ? currentRecord = new Record(LocalDate.now()) : currentRecord;
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
				currentRecord.setPhoneLandline(cleanPhoneNumber(value));
				break;
			case PHONE_MOBILE:
				currentRecord.setPhoneMobile(cleanPhoneNumber(value));
				break;
			case PHONE_MOBILE2:
				currentRecord.setPhoneMobile2(cleanPhoneNumber(value));
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
	}

	public boolean hasNewRecordInProgress() { return getCurrentRecord().isNew(); }

	public String getCurrentFullName() {
		StringBuilder result = new StringBuilder();

		result.append(currentRecord.getSecondName()).append(" ").append(currentRecord.getFirstName(), 0, 1);
		result.append(".");

		if (!currentRecord.getPatronym().isEmpty()) {
			result.append(" ").append(currentRecord.getPatronym(), 0, 1);
			result.append(".");
		}

		return result.toString();
	}

	public String getCurrentFullAddress() {
		StringBuilder result = new StringBuilder();

		if (!currentRecord.getAddressZip().isEmpty()) {
			result.append(currentRecord.getAddressZip()).append(" ");
		}

		result.append(currentRecord.getAddressCity()).append(", ").append(currentRecord.getAddressStreet()).append(", ");
		result.append(currentRecord.getAddressBuilding()).append(", ").append(currentRecord.getAddressApt());

		return result.toString();
	}

	public LocalDate getCurrentCreatedDate() { return currentRecord.getCreatedDate(); }

	public LocalDate getCurrentChangedDate() { return currentRecord.getChangedDate(); }

	public void saveCurrent() throws PersistanceException {
		currentRecord.setChangedDate(LocalDate.now()); //the time of change is NOW

		DaoFactory factory = DaoFactory.getInstance();
		RecordDao dao = factory.createRecordDao();
		dao.create(currentRecord);
	}

	private String cleanPhoneNumber(String rawNumber) {
		StringBuilder result = new StringBuilder();

		for (char n : rawNumber.toCharArray()) {
			if (Character.isDigit(n)) {
				result.append(n);
			}
		}

		return result.toString();
	}
}
