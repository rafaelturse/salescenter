package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.UserGroupDAO;
import br.com.salescenter.orm.permission.UserGroupORM;
import br.com.salescenter.vo.permission.FilterUserGroupVO;
import br.com.salescenter.vo.permission.UserGroupVO;

@RequestScoped
public class UserGroupService {

	@Inject
	private UserGroupDAO userGroupDAO;

	public void save(final UserGroupORM orm) {
		userGroupDAO.save(orm);
	}

	public void update(final UserGroupORM orm) {
		userGroupDAO.update(orm);
	}

	public UserGroupORM findById(final long id) {
		return userGroupDAO.findById(id);
	}

	public int rowFiltered() {
		return userGroupDAO.rowFiltered();
	}

	public List<UserGroupVO> filtered(final FilterUserGroupVO filter) {
		return userGroupDAO.filtered(filter);
	}

	public boolean existsUserGroupName(final String name) {
		return userGroupDAO.existsUserGroupName(name);
	}

	public boolean existsUserGroupName(final long id, final String name) {
		return userGroupDAO.existsUserGroupName(id, name);
	}

	public List<UserGroupORM> searchUserGroup(final String value) {
		return userGroupDAO.searchUserGroup(value);
	}
}
