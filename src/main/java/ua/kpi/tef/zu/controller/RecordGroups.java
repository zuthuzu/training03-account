package ua.kpi.tef.zu.controller;

/**
 * Created by Anton Domin on 2020-02-11
 */

public enum RecordGroups {
	PERSONAL(1, "group.personal"),
	BUSINESS(2, "group.business"),
	LOCAL(3, "group.local"),
	MISC(4, "group.misc");

	public static final String INPUT_OPTIONS = "[1-4]";

	private int inputKey;
	private String displayName;

	RecordGroups(int key, String value) {
		this.inputKey = key;
		this.displayName = value;
	}

	public int getInputKey() { return inputKey; }

	public String getDisplayName() { return displayName; }

	public static String getNameByKey(int key) {
		for (RecordGroups currentGroup : values()) {
			if (currentGroup.getInputKey() == key) { return currentGroup.getDisplayName(); }
		}

		return "";
	}
}
