package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum StreetTypeEnum {

	AEROPORTO(1, "enum.airport"),
	ALAMEDA(2, "enum.avenue"),
	AREA(3, "enum.area"),
	AVENIDA(4, "enum.avenue"),
	CAMPO(5, "enum.field"),
	CHACARA(6, "enum.chacara"),
	COLONIA(7, "enum.cologne"),
	CONDOMINIO(8, "enum.condominium"),
	CONJUNTO(9, "enum.set"),
	DISTRITO(10, "enum.district"),
	ESPLANADA(11, "enum.terrace"),
	ESTACAO(12, "enum.season"),
	ESTRADA(13, "enum.road"),
	FAVELA(14, "enum.shantyTown"),
	FEIRA(15, "enum.market"),
	JARDIM(16, "enum.garden"),
	LADEIRA(17, "enum.slope"),
	LAGO(18, "enum.lake"),
	LAGOA(19, "enum.lagoon"),
	LARGO(20, "enum.long"),
	LOTEAMENTO(21, "enum.allotment"),
	MORRO(22, "enum.hill"),
	NUCLEO(23, "enum.core"),
	PARQUE(24, "enum.park"),
	PASSARELA(25, "enum.footbridge"),
	PATIO(26, "enum.courtyard"),
	PRACA(27, "enum.square"),
	QUADRA(28, "enum.block"),
	RECANTO(29, "enum.recanto"),
	RESIDENCIAL(30, "enum.residential"),
	RODOVIA(31, "enum.highway"),
	RUA(32, "enum.street"),
	SETOR(33, "enum.sector"),
	SITIO(34, "enum.site"),
	TRAVESSA(35, "enum.travessa"),
	TRECHO(36, "enum.excerpt"),
	TREVO(37, "enum.clover"),
	VALE(38, "enum.valley"),
	VEREDA(39, "enum.path"),
	VIA(40, "enum.via"),
	VIADUTO(41, "enum.viaduct"),
	VIELA(42, "enum.alley"),
	VILA(43, "enum.village");

	private static final Map<Integer, StreetTypeEnum> LOOKUP = new HashMap<>();

	static {
		for (StreetTypeEnum e : StreetTypeEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	StreetTypeEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static StreetTypeEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}