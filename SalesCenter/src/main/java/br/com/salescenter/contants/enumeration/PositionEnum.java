package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum PositionEnum {

	ANALISTA(1, "enum.analyst"),
	APRENDIZ(2, "enum.apprentice"),
	DIRETOR(3, "enum.director"),
	ESPECIALISTA(4, "enum.specialist"),
	ESTAGIARIO(5, "enum.intern"),
	GERENTE(6, "enum.manager"),
	OPERADOR(7, "enum.operator"),
	SUPERVISOR(8, "enum.supervisor"),
	PRESIDENTE(9, "enum.president"),
	TRAINNE(10, "enum.trainee");

	private static final Map<Integer, PositionEnum> LOOKUP = new HashMap<>();

	static {
		for (PositionEnum e : PositionEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	PositionEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static PositionEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}