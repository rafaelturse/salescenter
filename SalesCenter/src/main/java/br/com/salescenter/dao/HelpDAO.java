package br.com.salescenter.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.help.HelpORM;
import br.com.salescenter.vo.help.FilterHelpVO;
import br.com.salescenter.vo.help.HelpVO;

public class HelpDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(HelpORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<HelpVO> filtered(FilterHelpVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		if (StringUtils.isNotEmpty(filter.getDoubt())) {
			criteria.add(Restrictions.ilike("name", filter.getDoubt(), MatchMode.ANYWHERE));
		}

		criteria.add(Restrictions.eq("status", StatusEnum.ATIVO));

		if (filter.isAscending() && filter.getOrder() != null) {
			criteria.addOrder(Order.asc(filter.getOrder()));
		} else if (filter.getOrder() != null) {
			criteria.addOrder(Order.desc(filter.getOrder()));
		}

		return criteria.list();
	}

	public HelpORM findById(final long id) {
		return entityManager.find(HelpORM.class, id);
	}

	public void save(final HelpORM orm) {
		entityManager.persist(orm);
	}

	public void update(final HelpORM orm) {
		entityManager.merge(orm);
	}
}