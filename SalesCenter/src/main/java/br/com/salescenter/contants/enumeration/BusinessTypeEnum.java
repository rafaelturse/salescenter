package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum BusinessTypeEnum {

	NEW(1, "enum.new"),
	RENOVATION(2, "enum.renovation");
	
	private static final Map<Integer, BusinessTypeEnum> LOOKUP = new HashMap<>();

	static {
		for (BusinessTypeEnum e : BusinessTypeEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	BusinessTypeEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static BusinessTypeEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}
