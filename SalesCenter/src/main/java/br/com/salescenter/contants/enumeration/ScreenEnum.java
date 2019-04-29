package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum ScreenEnum {

	HOME(1, "enum.home"),
	USER(2, "enum.user"),
	CLIENT(3, "enum.client"),
	USERGROUP(4, "enum.userGroup"),
	LANGUAGE(5, "enum.language"),
	THEME(6, "enum.theme"),
	HELP(7, "enum.help"),
	DASHBOARD(8, "enum.dashboard"),
	OPPORTUNITY(9, "enum.opportunity"),
	COMPENSATION(10, "enum.compensation");

	private static final Map<Integer, ScreenEnum> LOOKUP = new HashMap<>();

	static {
		for (ScreenEnum e : ScreenEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	ScreenEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static ScreenEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}
