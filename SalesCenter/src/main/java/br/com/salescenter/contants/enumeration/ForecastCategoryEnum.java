package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum ForecastCategoryEnum {

	PIPELINE(1, "enum.pipeline"),
	UPSIDE(2, "enum.upside"),
	COMMIT(3, "enum.commit"),
	WON(4, "enum.won"),
	OMMITED(5, "enum.ommited");

	private static final Map<Integer, ForecastCategoryEnum> LOOKUP = new HashMap<>();

	static {
		for (ForecastCategoryEnum e : ForecastCategoryEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	ForecastCategoryEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static ForecastCategoryEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}