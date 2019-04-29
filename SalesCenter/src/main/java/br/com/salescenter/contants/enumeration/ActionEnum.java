package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum ActionEnum {

	CREATE(1, "enum.create"),
	READ(2, "enum.read"),
	UPDATE(3, "enum.update"),
	DELETE(4, "enum.delete"),
	SEARCH(5, "enum.search"),
	ENABLE(6, "enum.enable"),
	DISABLE(7, "enum.disable"),
	DOWNLOAD(8, "enum.download"),
	UPLOAD(9, "enum.upload");
	
	private static final Map<Integer, ActionEnum> LOOKUP = new HashMap<>();

	static {
		for (ActionEnum e : ActionEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	ActionEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static ActionEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}