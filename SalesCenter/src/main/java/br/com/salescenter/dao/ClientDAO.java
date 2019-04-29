package br.com.salescenter.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.vo.client.ClientVO;
import br.com.salescenter.vo.client.FilterClientVO;
import lombok.extern.java.Log;

@Log
public class ClientDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(ClientORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<ClientVO> filtered(FilterClientVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		if (StringUtils.isNotEmpty(filter.getSocial())) {
			criteria.add(Restrictions.ilike("social", filter.getSocial(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getFantasy())) {
			criteria.add(Restrictions.ilike("fantasy", filter.getFantasy(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getCnpj())) {
			criteria.add(Restrictions.eq("cnpj", filter.getCnpj()));
		}

		if (StringUtils.isNotEmpty(filter.getStateRegistration())) {
			criteria.add(Restrictions.ilike("stateRegistration", filter.getStateRegistration(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getSite())) {
			criteria.add(Restrictions.ilike("site", filter.getSite(), MatchMode.ANYWHERE));
		}

		if (filter.getStatus() != null) {
			criteria.add(Restrictions.eq("status", filter.getStatus()));
		}

		criteria.add(Restrictions.between("inclusion", filter.getFrom(), filter.getTo()));

		if (filter.isAscending() && filter.getOrder() != null) {
			criteria.addOrder(Order.asc(filter.getOrder()));
		} else if (filter.getOrder() != null) {
			criteria.addOrder(Order.desc(filter.getOrder()));
		}

		return criteria.list();
	}

	public boolean existsCNPJ(final String cnpj) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsCNPJ", Long.class);
			query.setParameter("cnpj", cnpj);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsCNPJ");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsCNPJ(final long id, final String cnpj) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsIDAndCNPJ", Long.class);
			query.setParameter("id", id);
			query.setParameter("cnpj", cnpj);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsCNPJ");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsSocial(final String social) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsSocial", Long.class);
			query.setParameter("social", social);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsSocial");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsSocial(final long id, final String social) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsIDAndSocial", Long.class);
			query.setParameter("id", id);
			query.setParameter("social", social);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsSocial");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsFantasy(final String fantasy) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsFantasy", Long.class);
			query.setParameter("fantasy", fantasy);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsFantasy");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsFantasy(final long id, final String fantasy) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("ClientORM.existsIDAndFantasy", Long.class);
			query.setParameter("id", id);
			query.setParameter("fantasy", fantasy);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsFantasy");
			e.printStackTrace();
		}

		return false;
	}

	public List<ClientORM> listAll() {
		try {
			TypedQuery<ClientORM> query = entityManager.createNamedQuery("ClientORM.listAll", ClientORM.class);

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

	@SuppressWarnings("unchecked")
	public List<ClientORM> searchClient(final String value) {
		Criteria criteria = null;

		if (StringUtils.isNotBlank(value)) {
			criteria = createCriteriaForFilter();
			criteria.add(Restrictions.ilike("social", value, MatchMode.ANYWHERE));

			if (criteria.list().size() == 0) {
				criteria = createCriteriaForFilter();
				criteria.add(Restrictions.ilike("fantasy", value, MatchMode.ANYWHERE));
			}

			if (criteria.list().size() == 0) {
				criteria = createCriteriaForFilter();
				criteria.add(Restrictions.ilike("cnpj", value, MatchMode.ANYWHERE));
			}
		}

		return criteria.list();
	}

	public ClientORM findById(final long id) {
		return entityManager.find(ClientORM.class, id);
	}

	public void save(final ClientORM orm) {
		entityManager.persist(orm);
	}

	public void update(final ClientORM orm) {
		entityManager.merge(orm);
	}
}
