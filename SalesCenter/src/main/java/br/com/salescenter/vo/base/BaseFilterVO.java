package br.com.salescenter.vo.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseFilterVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int first;
	private int quantity;
	private String order;
	private boolean ascending;
}
