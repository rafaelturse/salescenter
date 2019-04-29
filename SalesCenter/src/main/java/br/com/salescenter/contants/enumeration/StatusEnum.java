package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum StatusEnum {

	INATIVO(0, "enum.inactive"),
	ATIVO(1, "enum.active");

	private static final Map<Integer, StatusEnum> LOOKUP = new HashMap<>();

	static {
		for (StatusEnum e : StatusEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	StatusEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static StatusEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}