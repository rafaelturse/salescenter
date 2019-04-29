package br.com.salescenter.controller.permission;

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
import br.com.salescenter.service.UserGroupService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.ValidationUtils;
import br.com.salescenter.vo.permission.FilterUserGroupVO;
import br.com.salescenter.vo.permission.UserGroupVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "searchUserGroupController")
@ViewScoped
public class SearchUserGroupController extends BaseCRUD implements Serializable {

	@Inject
	FilterUserGroupVO filter;

	@Inject
	UserGroupService userGroupService;

	private static final long serialVersionUID = 1L;

	private LazyDataModel<UserGroupVO> results = null;
	private StatusEnum[] status = StatusEnum.values();
	private boolean userGroupStatus;

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
		return "view-edit-user-group";
	}

	public String insertUser() {
		return "insert-user-group";
	}

	public void search() {
		if (validate()) {
			results = new LazyDataModel<UserGroupVO>() {

				private static final long serialVersionUID = 1L;

				@Override
				public List<UserGroupVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					filter.setFirst(first);
					filter.setQuantity(pageSize);
					filter.setAscending(SortOrder.ASCENDING.equals(sortOrder));
					filter.setOrder(sortField);

					setRowCount(userGroupService.rowFiltered());

					return userGroupService.filtered(filter);
				}
			};
		}
	}
}
