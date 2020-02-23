package ua.kpi.tef.zu.model;

/**
 * Created by Anton Domin on 2020-02-23
 */
public enum ExistingEmails {
	E1("test@mail.com"),
	E2("a@aa.aa"),
	E3("nn@ya.ru");

	private String value;

	ExistingEmails(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
