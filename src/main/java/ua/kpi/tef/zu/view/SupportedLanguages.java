package ua.kpi.tef.zu.view;

public enum SupportedLanguages {
	ENGLISH ("To select English, enter '1'."),
	RUSSIAN ("Чтобы выбрать русский язык, введите '2'.");

	private String userPrompt;

	SupportedLanguages(String userPrompt) { this.userPrompt = userPrompt; }

	public String getUserPrompt() {
		return userPrompt;
	}
}
