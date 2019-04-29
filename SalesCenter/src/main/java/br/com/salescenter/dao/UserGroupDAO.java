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

import br.com.salescenter.orm.permission.UserGroupORM;
import br.com.salescenter.vo.permission.FilterUserGroupVO;
import br.com.salescenter.vo.permission.UserGroupVO;
import lombok.extern.java.Log;

@Log
public class UserGroupDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(UserGroupORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupVO> filtered(FilterUserGroupVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		if (StringUtils.isNotEmpty(filter.getName())) {
			criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
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

	public boolean existsUserGroupName(final String name) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("UserGroupORM.existsUserGroupName", Long.class);
			query.setParameter("name", name);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsUserGroupName(final long id, final String name) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("UserGroupORM.existsIDAndGroupName", Long.class);
			query.setParameter("id", id);
			query.setParameter("name", name);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsUserGroupName");
			e.printStackTrace();
		}

		return false;
	}

	public List<UserGroupORM> listAll() {
		try {
			TypedQuery<UserGroupORM> query = entityManager.createNamedQuery("UserGroupORM.listAll", UserGroupORM.class);

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
	public List<UserGroupORM> searchUserGroup(final String value) {
		Criteria criteria = null;

		if (StringUtils.isNotBlank(value)) {
			criteria = createCriteriaForFilter();
			criteria.add(Restrictions.ilike("name", value, MatchMode.ANYWHERE));
		}

		return criteria.list();
	}

	public UserGroupORM findById(final long id) {
		return entityManager.find(UserGroupORM.class, id);
	}

	public void save(final UserGroupORM orm) {
		entityManager.persist(orm);
	}

	public void update(final UserGroupORM orm) {
		entityManager.merge(orm);
	}
}