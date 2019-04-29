package br.com.salescenter.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.UserDAO;
import br.com.salescenter.orm.user.UserORM;

@RequestScoped
public class UserService {

	@Inject
	private UserDAO userDAO;

	public UserORM findByLoginAndPassword(final String login, final String password) {
		return userDAO.findByLoginAndPassword(login, password);
	}

	public UserORM findById(final long id) {
		return userDAO.findById(id);
	}

	public UserORM findByLoginAndPasswordFromTheme(final String login, final String password) {
		return new UserDAO().findByLoginAndPassword(login, password);
	}

	public boolean existsLogin(final String login) {
		return userDAO.existsLogin(login);
	}

	public boolean existsLogin(final long id, final String login) {
		return userDAO.existsLogin(id, login);
	}

	public boolean existsEmployeeNumber(final String employeeNumber) {
		return userDAO.existsEmployeeNumber(employeeNumber);
	}

	public void updateTheme(final String theme, final Long id) {
		new UserDAO().updateTheme(theme, id);
	}

	public void updateLanguage(final String language, final Long id) {
		new UserDAO().updateLanguage(language, id);
	}
}
