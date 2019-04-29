package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum CompetitorTypeEnum {

	MAIN(1, "enum.main"),
	COMPETITOR(1, "enum.competitor");

	private static final Map<Integer, CompetitorTypeEnum> LOOKUP = new HashMap<>();

	static {
		for (CompetitorTypeEnum e : CompetitorTypeEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	CompetitorTypeEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static CompetitorTypeEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}