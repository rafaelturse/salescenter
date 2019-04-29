package br.com.salescenter.vo.opportunity;

import java.io.Serializable;

import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PendingForecastCategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ForecastCategoryEnum forecastCategory;
	private Integer qtd;
	private Integer pending;
	private Integer size;
}