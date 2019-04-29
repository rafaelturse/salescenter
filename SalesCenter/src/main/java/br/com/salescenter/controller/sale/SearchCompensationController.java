package br.com.salescenter.controller.sale;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.CompensationService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.ValidationUtils;
import br.com.salescenter.vo.compensation.CompensationVO;
import br.com.salescenter.vo.compensation.FilterCompensationVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchCompensationController")
@ViewScoped
public class SearchCompensationController extends BaseCRUD implements Serializable {

	@Inject
	FilterCompensationVO filter;

	@Inject
	CompensationService compensationService;

	private static final long serialVersionUID = 1L;

	private LazyDataModel<CompensationVO> results = null;
	private StatusEnum[] status = StatusEnum.values();

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
		return "view-edit-compensation";
	}

	public String insert() {
		return "insert-compensation";
	}

	public void search() {
		if (validate()) {
			results = new LazyDataModel<CompensationVO>() {

				private static final long serialVersionUID = 1L;

				@Override
				public List<CompensationVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					filter.setFirst(first);
					filter.setQuantity(pageSize);
					filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
					filter.setOrder(sortField);

					setRowCount(compensationService.rowFiltered());

					return compensationService.filtered(filter);
				}
			};
		}
	}
}
