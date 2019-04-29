package br.com.salescenter.dao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.salescenter.orm.user.UserORM;
import lombok.extern.java.Log;

@Log
public class UserDAO extends BaseDAO {

	@Inject
	UserORM userORM;

	public UserORM findByLoginAndPassword(final String login, final String password) {
		try {
			TypedQuery<UserORM> query = entityManager.createNamedQuery("UserORM.findByLoginAndPassword", UserORM.class);
			query.setParameter("login", login);
			query.setParameter("password", password);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method findByLoginAndPassword");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method findByLoginAndPassword");
			e.printStackTrace();
		}

		return null;
	}

	public UserORM findById(final long id) {
		return entityManager.find(UserORM.class, id);
	}

	public boolean existsEmployeeNumber(final String employeeNumber) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("UserORM.existsEmployeeNumber", Long.class);
			query.setParameter("employeeNumber", employeeNumber);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsEmployeeNumber");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsLogin(final String login) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("UserORM.existsLogin", Long.class);
			query.setParameter("login", login);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsLogin");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsLogin(final long id, final String login) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("UserORM.existsIDAndLogin", Long.class);
			query.setParameter("id", id);
			query.setParameter("login", login);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsLogin");
			e.printStackTrace();
		}

		return false;
	}

	public void updateTheme(final String theme, final Long id) {
		try {
			Query query = entityManager.createNamedQuery("UserORM.updateTheme");
			query.setParameter("id", id);
			query.setParameter("theme", theme);

			query.executeUpdate();
		} catch (IllegalArgumentException e) {
			log.severe("Error on method updateTheme");
			e.printStackTrace();
		}
	}

	public void updateLanguage(final String language, final Long id) {
		try {
			Query query = entityManager.createNamedQuery("UserORM.updateLanguage");
			query.setParameter("id", id);
			query.setParameter("language", language);

			query.executeUpdate();
		} catch (IllegalArgumentException e) {
			log.severe("Error on method updateLanguage");
			e.printStackTrace();
		}
	}
}
