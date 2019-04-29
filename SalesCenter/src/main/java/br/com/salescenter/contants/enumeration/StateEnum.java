package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum StateEnum {

	AC(1, "enum.acre"),
	AL(2, "enum.alagoas"),
	AP(3, "enum.amapa"),
	AM(4, "enum.amazonas"),
	BA(5, "enum.bahia"),
	CE(6, "enum.ceara"),
	DF(7, "enum.distritoFederal"),
	ES(8, "enum.espiritoSanto"),
	GO(9, "enum.goias"),
	MA(10, "enum.maranhao"),
	MG(11, "enum.minasGerais"),
	MS(12, "enum.matoGrossoSul"),
	MT(13, "enum.matoGrosso"),
	PA(14, "enum.para"),
	PB(15, "enum.paraiba"),
	PE(16, "enum.pernambuco"),
	PI(17, "enum.piaui"),
	PR(18, "enum.parana"),
	RJ(19, "enum.rioJaneiro"),
	RN(20, "enum.rioGrandeNorte"),
	RO(21, "enum.rondonia"),
	RR(22, "enum.roraima"),
	RS(23, "enum.rioGrandeSul"),
	SC(24, "enum.santaCatarina"),
	SE(25, "enum.sergipe"),
	SP(26, "enum.saoPaulo"),
	TO(27, "enum.tocantins");

	private static final Map<Integer, StateEnum> LOOKUP = new HashMap<>();

	static {
		for (StateEnum e : StateEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	StateEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static StateEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}