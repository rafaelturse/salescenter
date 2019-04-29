package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum SegmentEnum {

	ACESSORIOS(1, "enum.accessories"),
	ADVOCACIA(2, "enum.advocacy"),
	AEROPORTOS(3, "enum.airports"),
	AGRICULTURA(4, "enum.agriculture"),
	ALIMENTACAO(5, "enum.food"),
	ANIMAIS(6, "enum.animals"),
	ANTIGUIDADES(7, "enum.antiques"),
	ANUNCIOS(8, "enum.adverts"),
	ARMAZENAGEM(9, "enum.storage"),
	ARQUITETURA(10, "enum.architecture"),
	ARTE(11, "enum.art"),
	ARTIGOSRELIGIOSOS(12, "enum.religiousArticles"),
	ASSESSORIAS(13, "enum.advisors"),
	ASSISTENCIAIS(14, "enum.assistants"),
	AUDIO(15, "enum.audio"),
	BANCOS(16, "enum.banks"),
	BEBIDAS(17, "enum.drinks"),
	BIBLIOTECAS(18, "enum.libraries"),
	BRINQUEDOS(19, "enum.toys"),
	CALCADO(20, "enum.shoe"),
	CAMBIO(21, "enum.exchange"),
	CARNES(22, "enum.meat"),
	CARTORIOS(23, "enum.cartography"),
	COLECIONAVEIS(24, "enum.collections"),
	COMBUSTIVEIS(25, "enum.fuels"),
	COMPRASCOLETIVAS(26, "enum.coletiveShopping"),
	COMUNICACAO(27, "enum.communication"),
	CONFECCOES(28, "enum.clothing"),
	CONFEITARIA(29, "enum.confectionery"),
	CONSERTOS(30, "enum.conserts"),
	CONSORCIOS(31, "enum.consortiums"),
	CONSTRUCAO(32, "enum.construction"),
	CONSULTORIAS(33, "enum.consulting"),
	CONTAS(34, "enum.bills"),
	COOPERATIVAS(35, "enum.cooperatives"),
	COUROS(36, "enum.leather"),
	CULTURA(37, "enum.culture"),
	CURSOS(38, "enum.courses"),
	DANCA(39, "enum.dance"),
	DECORACAO(40, "enum.decoration"),
	SOFTWARE(41, "enum.software"),
	DESIGN(42, "enum.design"),
	DESPACHANTE(43, "enum.forwardingAgent"),
	DISCOS(44, "enum.discs"),
	ECOLOGIA(45, "enum.ecology"),
	EDUCACAO(46, "enum.education"),
	ELETRODOMESTICOS(47, "enum.homeAppliances"),
	ELETRONICOS(48, "enum.electronics"),
	EMISSORASRADIO(49, "enum.radioIssuer"),
	EMISSORASTELEVISAO(50, "enum.televisionIssuer"),
	EMPREGOS(51, "enum.employment"),
	ESOTERICOS(52, "enum.esoterics"),
	ESPORTE(53, "enum.sport"),
	ESTACIONAMENTOS(54, "enum.parking"),
	ESTALEIROS(55, "enum.landscapes"),
	ESTETICA(56, "enum.aesthetic"),
	EVENTOS(57, "enum.events"),
	EXPORTACAO(58, "enum.export"),
	IMPORTACAO(59, "enum.import"),
	FERRAGENS(60, "enum.hardware"),
	FILMAGEM(61, "enum.filming"),
	FINANCAS(62, "enum.financing"),
	FISCAL(63, "enum.supervisor"),
	FITNESS(64, "enum.fitness"),
	FOTOGRAFIA(65, "enum.photography"),
	FUNERARIOS(66, "enum.funeral"),
	GOVERNO(67, "enum.government"),
	GRAFICA(68, "enum.printShop"),
	HORTIFRUT(69, "enum.hortifrut"),
	HOTEL(70, "enum.hotel"),
	IMOVEIS(71, "enum.properties"),
	IMPRESSORA(72, "enum.printer"),
	ESCANER(73, "enum.scan"),
	INFORMATICA(74, "enum.computing"),
	INGRESSOS(75, "enum.tickets"),
	INSTRUMENTOSMUSICAIS(76, "enum.musicalInstruments"),
	JOALHERIA(77, "enum.jewelry"),
	JORNAIS(78, "enum.newspapers"),
	LAZER(79, "enum.recreation"),
	LEILOEIROS(80, "enum.auctioneers"),
	LIMPEZA(81, "enum.cleaning"),
	LIXO(82, "enum.trash"),
	LIVROS(83, "enum.books"),
	LOCACAO(84, "enum.location"),
	LOTERICAS(85, "enum.loterics"),
	MARKETING(86, "enum.marketing"),
	PROMOCIONAIS(87, "enum.promotional"),
	MECANICA(88, "enum.mechanics"),
	MEIOAMBIENTE(89, "enum.environment"),
	METALURGIA(90, "enum.metallurgy"),
	MOBILIARIO(91, "enum.furniture"),
	MODA(92, "enum.fashion"),
	MOTEL(93, "enum.motel"),
	MUSICA(94, "enum.music"),
	PAPELARIA(95, "enum.stationaryStore"),
	PECUARIA(96, "enum.livestock"),
	PERFUMES(97, "enum.perfumes"),
	PESQUISAS(98, "enum.researches"),
	PISCICULTURA(99, "enum.pisciculture"),
	PLANTAS(100, "enum.plants"),
	PORTOS(101, "enum.ports"),
	RECURSOSHUMANOS(102, "enum.humanResources"),
	REDES(103, "enum.networks"),
	RELIGIAO(104, "enum.religion"),
	REVISTAS(105, "enum.magazines"),
	ROUPAS(106, "enum.clothes"),
	SAUDE(107, "enum.cheers"),
	SEGURADORAS(108, "enum.insurers"),
	SEGURANCA(109, "enum.safety"),
	AVALIACAO(110, "enum.evaluation"),
	SEXO(111, "enum.sex"),
	SINALIZACAO(112, "enum.signaling"),
	SINDICATOS(113, "enum.unions"),
	STREAMING(114, "enum.streaming"),
	TABACARIA(115, "enum.tobacco"),
	TARIFAS(116, "enum.rates"),
	TECIDOS(117, "enum.fabrics"),
	TELEFONIA(118, "enum.telephony"),
	TELEMARKETING(119, "enum.telemarketing"),
	TRADUCAES(120, "enum.translations"),
	TRANSPORTE(121, "enum.transport"),
	TURISMO(122, "enum.tourism"),
	VEICULOS(123, "enum.vehicles"),
	VESTUARIO(124, "enum.clothing"),
	VIDEOGAMES(125, "enum.videogames");

	private static final Map<Integer, SegmentEnum> LOOKUP = new HashMap<>();

	static {
		for (SegmentEnum e : SegmentEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	SegmentEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static SegmentEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}