package br.com.salescenter.vo.base;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.salescenter.contants.enumeration.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate inclusion;
	private LocalDate lastUpdate;
	private StatusEnum status;
	private LocalDate from;
	private LocalDate to;
}
