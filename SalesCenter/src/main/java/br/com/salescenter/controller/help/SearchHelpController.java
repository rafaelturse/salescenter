package br.com.salescenter.controller.help;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.HelpService;
import br.com.salescenter.vo.help.FilterHelpVO;
import br.com.salescenter.vo.help.HelpVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchHelpController")
@ViewScoped
public class SearchHelpController extends BaseCRUD implements Serializable {

	@Inject
	FilterHelpVO filter;

	@Inject
	HelpService helpService;

	private static final long serialVersionUID = 1L;

	private LazyDataModel<HelpVO> results = null;

	public String viewEditHelp() {
		return "view-edit-help";
	}

	public String insertHelp() {
		return "insert-help";
	}

	public void search() {
		results = new LazyDataModel<HelpVO>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<HelpVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filter.setFirst(first);
				filter.setQuantity(pageSize);
				filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
				filter.setOrder(sortField);

				setRowCount(helpService.rowFiltered());

				return helpService.filtered(filter);
			}
		};
	}
}
