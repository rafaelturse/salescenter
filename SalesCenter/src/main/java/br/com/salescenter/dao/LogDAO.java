package br.com.salescenter.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.orm.permission.LogORM;
import lombok.extern.java.Log;

@Log
public class LogDAO extends BaseDAO {

	public List<LogORM> listAll(final ScreenEnum screen, final long reference) {
		try {
			TypedQuery<LogORM> query = entityManager.createNamedQuery("LogORM.listAll", LogORM.class);
			query.setParameter("screen", screen);
			query.setParameter("reference", reference);

			return query.getResultList();
		} catch (NoResultException e) {
			log.severe("Error on method listAll");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method listAll");
			e.printStackTrace();
		}

		return null;
	}

	public void save(final LogORM orm) {
		entityManager.persist(orm);
	}
}
