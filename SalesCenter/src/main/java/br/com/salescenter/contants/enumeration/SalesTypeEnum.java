package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum SalesTypeEnum {

	NORMAL(1, "enum.normal"),
	LEASING(2, "enum.leasing");

	private static final Map<Integer, SalesTypeEnum> LOOKUP = new HashMap<>();

	static {
		for (SalesTypeEnum e : SalesTypeEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	SalesTypeEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static SalesTypeEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}