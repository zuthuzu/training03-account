package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Record {
	private boolean isNew;
	private String firstName;
	private String secondName;
	private String userLogin;

	public Record() {
		isNew = true;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public boolean isNew() {
		return isNew;
	}

}
