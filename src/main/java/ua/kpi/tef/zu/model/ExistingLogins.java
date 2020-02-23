package ua.kpi.tef.zu.model;

/**
 * Created by Anton Domin on 2020-02-23
 */
public enum ExistingLogins {
	AA("aa"),
	N1("n1"),
	N2("n2");

	private String value;

	ExistingLogins(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
