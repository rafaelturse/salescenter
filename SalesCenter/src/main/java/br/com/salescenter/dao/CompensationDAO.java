package br.com.salescenter.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.salescenter.orm.sale.CompensationORM;
import br.com.salescenter.vo.compensation.CompensationVO;
import br.com.salescenter.vo.compensation.FilterCompensationVO;

public class CompensationDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(CompensationORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<CompensationVO> filtered(FilterCompensationVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		if (StringUtils.isNotEmpty(filter.getSeller())) {
			criteria.add(Restrictions.ilike("seller", filter.getSeller(), MatchMode.ANYWHERE));
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

	public CompensationORM findById(final long id) {
		return entityManager.find(CompensationORM.class, id);
	}

	public void save(final CompensationORM orm) {
		entityManager.persist(orm);
	}

	public void update(final CompensationORM orm) {
		entityManager.merge(orm);
	}
}
