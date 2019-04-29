package br.com.salescenter.vo.opportunity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.vo.base.BaseFilterVO;
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
@RequestScoped
public class FilterOpportunityVO extends BaseFilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String manufacturerRegistration;
	private LocalDate closeDate;
	private BusinessTypeEnum businessType;
	private SalesStageEnum salesStage;
	private ForecastCategoryEnum forecastCategory;
	private ReasonEnum reason;
}
