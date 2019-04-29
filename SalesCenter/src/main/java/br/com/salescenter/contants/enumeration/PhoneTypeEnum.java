package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum PhoneTypeEnum {

	CELULAR(1, "enum.cel", "9-9999-9999"),
	RAMAL(2, "enum.extension", "9999"),
	RESIDENCIAL(3, "enum.homePhone", "9999-9999"),
	TRABALHO(4, "enum.work", "9999-9999");

	private static final Map<Integer, PhoneTypeEnum> LOOKUP = new HashMap<>();
	private static final Map<String, PhoneTypeEnum> LOOKUP_DESCRIPTION = new HashMap<>();

	static {
		for (PhoneTypeEnum e : PhoneTypeEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}
	
	static {
		for (PhoneTypeEnum e : PhoneTypeEnum.values()) {
			LOOKUP_DESCRIPTION.put(e.getDescription(), e);
		}
	}

	private final Integer id;
	private final String description;
	private final String mask;

	PhoneTypeEnum(Integer id, String description, String mask) {
		this.id = id;
		this.description = description;
		this.mask = mask;
	}

	public static PhoneTypeEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
	
	public static PhoneTypeEnum valueOfDescription(String description) {
		return description != null ? LOOKUP_DESCRIPTION.get(description) : null;
	}
}