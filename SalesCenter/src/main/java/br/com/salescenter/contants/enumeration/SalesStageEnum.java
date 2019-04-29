package br.com.salescenter.contants.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum SalesStageEnum {

	UNDERSTAND_CUSTOMER(1, "enum.understandCustomer"),
	VALIDATE_OPPORTUNITY(2, "enum.validateOpportunity"),
	QUALIFY_OPPORTUNITY(3, "enum.qualifyOpportunity"),
	DEVELOP_SOLUTION(4, "enum.developSolution"),
	PROPOSE_SOLUTION(5, "enum.proposeSolution"),
	NEGOTIATE_CLOSE(6, "enum.negotiateClose"),
	WON_DEPLOY_EXPAND(7, "enum.wonDeployExpand"),
	LOST(8, "enum.lost"),
	NOT_PURSUED(9, "enum.notPursued"),
	ERROR(10, "enum.error");

	private static final Map<Integer, SalesStageEnum> LOOKUP = new HashMap<>();

	static {
		for (SalesStageEnum e : SalesStageEnum.values()) {
			LOOKUP.put(e.getId(), e);
		}
	}

	private final Integer id;
	private final String description;

	SalesStageEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public static SalesStageEnum valueOfId(Integer id) {
		return id != null ? LOOKUP.get(id) : null;
	}
}