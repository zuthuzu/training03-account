package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Model {
	private Record currentRecord;

	private Record getCurrentRecord() {
		return (currentRecord == null) ? currentRecord = new Record() : currentRecord;
	}

	public void recordInput(String value, String fieldID) {
		if (value.isEmpty() || fieldID.isEmpty()) {
			return;
		}

		String lowercaseID = fieldID.toLowerCase();

		switch (lowercaseID) {
			case ("firstname"):
				getCurrentRecord().setFirstName(value);
				break;
			case ("secondname"):
				getCurrentRecord().setSecondName(value);
				break;
			case ("login"):
				getCurrentRecord().setUserLogin(value);
				break;
		}
	}

	public boolean hasNewRecordInProgress() {
		return getCurrentRecord().isNew();
	}
}
