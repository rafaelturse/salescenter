package br.com.salescenter.controller.client;

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
import br.com.salescenter.service.ClientService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.ValidationUtils;
import br.com.salescenter.vo.client.ClientVO;
import br.com.salescenter.vo.client.FilterClientVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchClientController")
@ViewScoped
public class SearchClientController extends BaseCRUD implements Serializable {

	@Inject
	FilterClientVO filter;

	@Inject
	ClientService clientService;

	private static final long serialVersionUID = 1L;

	private LazyDataModel<ClientVO> results = null;
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

	public String viewEditClient() {
		return "view-edit-client";
	}

	public String insertClient() {
		return "insert-client";
	}

	public void search() {
		if (validate()) {
			results = new LazyDataModel<ClientVO>() {

				private static final long serialVersionUID = 1L;

				@Override
				public List<ClientVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					filter.setFirst(first);
					filter.setQuantity(pageSize);
					filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
					filter.setOrder(sortField);

					setRowCount(clientService.rowFiltered());

					return clientService.filtered(filter);
				}
			};
		}
	}
}
