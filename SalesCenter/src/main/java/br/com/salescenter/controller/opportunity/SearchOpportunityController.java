package br.com.salescenter.controller.opportunity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.OpportunityService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.ValidationUtils;
import br.com.salescenter.vo.opportunity.FilterOpportunityVO;
import br.com.salescenter.vo.opportunity.OpportunityVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchOpportunityController")
@ViewScoped
public class SearchOpportunityController extends BaseCRUD implements Serializable {

	@Inject
	FilterOpportunityVO filter;

	@Inject
	OpportunityService opportunityService;

	private static final long serialVersionUID = 1L;

	private LazyDataModel<OpportunityVO> results = null;
	private BusinessTypeEnum[] bussinessType = BusinessTypeEnum.values();
	private ForecastCategoryEnum[] forecastCategory = ForecastCategoryEnum.values();
	private ReasonEnum[] reason = ReasonEnum.values();
	private StatusEnum[] status = StatusEnum.values();
	private SalesStageEnum[] salesStage = SalesStageEnum.values();

	@PostConstruct
	public void init() {
		filter.setFrom(DateTimeUtils.today());
		filter.setTo(DateTimeUtils.today());
	}

	private boolean validate() {
		if (!ValidationUtils.coherentDates(filter.getFrom(), filter.getTo())) { return false; }

		return true;
	}

	public String viewEdit() {
		return "view-edit-opportunity";
	}

	public String insert() {
		return "insert-opportunity";
	}

	public void search() {
		if (validate()) {
			results = new LazyDataModel<OpportunityVO>() {

				private static final long serialVersionUID = 1L;

				@Override
				public List<OpportunityVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					filter.setFirst(first);
					filter.setQuantity(pageSize);
					filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
					filter.setOrder(sortField);

					setRowCount(opportunityService.rowFiltered());

					return opportunityService.filtered(filter);
				}
			};
		}
	}
}
