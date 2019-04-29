package br.com.salescenter.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.salescenter.orm.compliment.UploadORM;
import lombok.extern.java.Log;

@Log
public class UploadDAO extends BaseDAO {

	public void upLoad(final UploadORM orm) {
		entityManager.persist(orm);
	}

	public List<UploadORM> findUploadedFiles(final Long opportunityId) {
		try {
			TypedQuery<UploadORM> query = entityManager.createNamedQuery("UploadORM.findUploadedFiles",
					UploadORM.class);
			query.setParameter("opportunityId", opportunityId);

			return query.getResultList();
		} catch (NoResultException e) {
			log.severe("Error on method findUploadedFiles");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method findUploadedFiles");
			e.printStackTrace();
		}

		return null;
	}

	public UploadORM findById(final long id) {
		return entityManager.find(UploadORM.class, id);
	}

	public void removeById(long id) {
		entityManager.remove(findById(id));
	}
}