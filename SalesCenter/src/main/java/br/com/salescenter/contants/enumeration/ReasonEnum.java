package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum ReasonEnum {

	WON_REPUTATION(1, "enum.wonReputation"),
	WON_OTHER_COMMERCIAL_TERMS(2, "enum.wonOtherCommercialTerms"),
	WON_SERVICE_OFFERED(3, "enum.wonServiceOffered"),
	WON_CUSTOMER_RELATIONSHIP(4, "enum.wonCustomerRelationship"),
	WON_PRICE(5, "enum.wonPrice"),
	LOST_CUSTOMER_CANCELED(6, "enum.lostCustomerCanceled"),
	LOST_CUSTOMER_DOES_NOT_HAVE_MONEY(7, "enum.lostCustomerDoesNotHaveMoney"),
	LOST_LEGAL_TERMS(8, "enum.lostLegalTerms"),
	LOST_PRICE(9, "enum.lostPrice"),
	LOST_PRODUCT_OFFERED(10, "enum.lostProductOffered"),
	LOST_CUSTOMER_RELATIONSHIP(11, "enum.lostCustomerRelationship"),
	LOST_SERVICE_OFFERED(12, "enum.lostServiceOffered"),
	LOST_OTHER_COMMERCIAL_TERMS(13, "enum.lostOtherCommercialTerms"),
	LOST_REPUTATION(14, "enum.lostReputation");

	private static final Map<Integer, ReasonEnum> LOOKUP = new HashMap<>();

	static {
		for (ReasonEnum e : ReasonEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	ReasonEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static ReasonEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}