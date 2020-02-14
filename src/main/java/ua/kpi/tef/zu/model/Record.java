package ua.kpi.tef.zu.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Record {
	private boolean isNew;
	private String firstName;
	private String secondName;
	private String patronym;
	private String login;
	private String comment;
	private String group;
	private String phoneLandline;
	private String phoneMobile;
	private String phoneMobile2;
	private String email;
	private String skype;
	private String addressZip;
	private String addressCity;
	private String addressStreet;
	private String addressBuilding;
	private String addressApt;
	private Date createdDate;
	private Date changedDate;

	private Calendar calendar = Calendar.getInstance();

	public Record() {
		isNew = true;
		createdDate = calendar.getTime();
		changedDate = calendar.getTime();
	}

	public void setChangedDateToCurrent() {
		changedDate = calendar.getTime();
	}

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public void setSecondName(String secondName) { this.secondName = secondName; }

	public void setPatronym(String patronym) { this.patronym = patronym; }

	public void setLogin(String login) { this.login = login; }

	public void setComment(String comment) { this.comment = comment; }

	public void setGroup(String group)  { this.group = group; }

	public void setPhoneLandline(String phoneLandline)  { this.phoneLandline = phoneLandline; }

	public void setPhoneMobile(String phoneMobile)  { this.phoneMobile = phoneMobile; }

	public void setPhoneMobile2(String phoneMobile2)  { this.phoneMobile2 = phoneMobile2; }

	public void setEmail(String email)  { this.email = email; }

	public void setSkype(String skype)  { this.skype = skype; }

	public void setAddressZip(String addressZip)  { this.addressZip = addressZip; }

	public void setAddressCity(String addressCity)  { this.addressCity = addressCity; }

	public void setAddressStreet(String addressStreet)  { this.addressStreet = addressStreet; }

	public void setAddressBuilding(String addressBuilding)  { this.addressBuilding = addressBuilding; }

	public void setAddressApt(String addressApt)  { this.addressApt = addressApt; }

	public String getFirstName() { return firstName; }

	public String getSecondName() { return secondName; }

	public String getPatronym() { return patronym; }

	public String getLogin() { return login; }

	public String getComment() { return comment; }

	public String getGroup() { return group; }

	public String getPhoneLandline() { return phoneLandline; }

	public String getPhoneMobile() { return phoneMobile; }

	public String getPhoneMobile2() { return phoneMobile2; }

	public String getEmail() { return email; }

	public String getSkype() { return skype; }

	public String getAddressZip() { return addressZip; }

	public String getAddressCity() { return addressCity; }

	public String getAddressStreet() { return addressStreet; }

	public String getAddressBuilding() { return addressBuilding; }

	public String getAddressApt() { return addressApt; }

	public boolean isNew() { return isNew; }

	public Date getChangedDate() { return changedDate; }

	public Date getCreatedDate() { return createdDate; }

	//Combo fields
	public String getFullName() {
		StringBuilder result = new StringBuilder();

		result.append(secondName).append(" ").append(firstName, 0, 1);

		if (!patronym.isEmpty()) {
			result.append(" ").append(patronym, 0, 1);
		}

		return result.toString();
	}

	public String getFullAddress() {
		StringBuilder result = new StringBuilder();

		if (!addressZip.isEmpty()) {
			result.append(addressZip);
		}

		result.append(addressCity).append(", ").append(addressStreet).append(", ");
		result.append(addressBuilding).append(", ").append(addressApt);

		return result.toString();
	}
}
