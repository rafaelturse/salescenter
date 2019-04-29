package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum GenderEnum {

	FEMALE(1, "enum.female"),
	MALE(2, "enum.male");

	private static final Map<Integer, GenderEnum> LOOKUP = new HashMap<>();

	static {
		for (GenderEnum e : GenderEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	GenderEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static GenderEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}