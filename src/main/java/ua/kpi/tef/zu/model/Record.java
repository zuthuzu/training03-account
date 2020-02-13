package ua.kpi.tef.zu.model;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Record {
	private boolean isNew;
	private String firstName;
	private String secondName;
	private String patronym;
	private String userLogin;
	private String userComment;

	public Record() { isNew = true; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public void setSecondName(String secondName){ this.secondName = secondName;	}

	public void setPatronym(String patronym) { this.patronym = patronym; }

	public void setUserLogin(String userLogin) { this.userLogin = userLogin; }

	public void setUserComment(String userComment) { this.userComment = userComment; }

	public String getFirstName() { return firstName; }

	public String getSecondName() { return secondName; }

	public String getPatronym() { return patronym; }

	public String getUserLogin() { return userLogin; }

	public String getUserComment() { return userComment; }

	public boolean isNew() { return isNew; }

}
