package br.com.salescenter.controller.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.PositionEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.PersonService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.ValidationUtils;
import br.com.salescenter.vo.user.FilterPersonVO;
import br.com.salescenter.vo.user.PersonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchUserController")
@ViewScoped
public class SearchUserController extends BaseCRUD implements Serializable {

	@Inject
	FilterPersonVO filter;

	@Inject
	PersonService personService;

	private static final long serialVersionUID = 1L;

	private GenderEnum[] genders = GenderEnum.values();
	private PositionEnum[] positions = PositionEnum.values();
	private LazyDataModel<PersonVO> results = null;
	private StatusEnum[] status = StatusEnum.values();
	private boolean userStatus;

	@PostConstruct
	public void init() {
		filter.setFrom(DateTimeUtils.today());
		filter.setTo(DateTimeUtils.today());
	}

	private boolean validate() {
		if (!ValidationUtils.coherentDates(filter.getFrom(), filter.getTo())) { return false; }

		return true;
	}

	public String viewEditUser() {
		return "view-edit-user";
	}

	public String insertUser() {
		return "insert-user";
	}

	public void search() {
		if (validate()) {
			results = new LazyDataModel<PersonVO>() {

				private static final long serialVersionUID = 1L;

				@Override
				public List<PersonVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					filter.setFirst(first);
					filter.setQuantity(pageSize);
					filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
					filter.setOrder(sortField);

					setRowCount(personService.rowFiltered());

					return personService.filtered(filter);
				}
			};
		}
	}
}
