package br.com.salescenter.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.SalesTypeEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import br.com.salescenter.vo.opportunity.FilterOpportunityVO;
import br.com.salescenter.vo.opportunity.OpportunityVO;
import br.com.salescenter.vo.opportunity.PendingForecastCategoryVO;
import br.com.salescenter.vo.opportunity.SalesAmountVO;
import br.com.salescenter.vo.opportunity.SellerCompensationVO;
import br.com.salescenter.vo.opportunity.SellerXSalesVO;
import lombok.extern.java.Log;

@Log
public class OpportunityDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(OpportunityORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<OpportunityVO> filtered(FilterOpportunityVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		if (StringUtils.isNotEmpty(filter.getName())) {
			criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getManufacturerRegistration())) {
			criteria.add(Restrictions.ilike("manufacturerRegistration", filter.getManufacturerRegistration(),
					MatchMode.ANYWHERE));
		}

		if (filter.getCloseDate() != null) {
			criteria.add(Restrictions.eq("closeDate", filter.getCloseDate()));
		}

		if (filter.getBusinessType() != null) {
			criteria.add(Restrictions.eq("businessType", filter.getBusinessType()));
		}

		if (filter.getSalesStage() != null) {
			criteria.add(Restrictions.eq("salesStage", filter.getSalesStage()));
		}

		if (filter.getForecastCategory() != null) {
			criteria.add(Restrictions.eq("forecastCategory", filter.getForecastCategory()));
		}

		if (filter.getReason() != null) {
			criteria.add(Restrictions.eq("reason", filter.getReason()));
		}

		criteria.add(Restrictions.between("inclusion", filter.getFrom(), filter.getTo()));

		if (filter.isAscending() && filter.getOrder() != null) {
			criteria.addOrder(Order.asc(filter.getOrder()));
		} else if (filter.getOrder() != null) {
			criteria.addOrder(Order.desc(filter.getOrder()));
		}

		return criteria.list();
	}

	public Long amountBusinessType(final BusinessTypeEnum businessType) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountBusinessType", Long.class);
			query.setParameter("businessType", businessType);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountBusinessType");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountBusinessType");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountForecastCategory(final ForecastCategoryEnum forecastCategory) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountForecastCategory",
					Long.class);
			query.setParameter("forecastCategory", forecastCategory);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountForecastCategory");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountForecastCategory");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountReason(final ReasonEnum reason) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountReason", Long.class);
			query.setParameter("reason", reason);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountReason");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountReason");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountSalesStage(final SalesStageEnum salesStage) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountSalesStage", Long.class);
			query.setParameter("salesStage", salesStage);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountSalesStage");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountSalesStage");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountSalesType(final SalesTypeEnum salesType) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountSalesType", Long.class);
			query.setParameter("salesType", salesType);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountSalesType");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountSalesType");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountStatusOpportunities(final StatusEnum status) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountStatusOpportunities",
					Long.class);
			query.setParameter("status", status);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountStatusOpportunities");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountStatusOpportunities");
			e.printStackTrace();
		}

		return null;
	}

	public Long amountWonXLost(final ForecastCategoryEnum forecastCategory) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.amountWonXLost", Long.class);
			query.setParameter("forecastCategory", forecastCategory);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountWonXLost");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountWonXLost");
			e.printStackTrace();
		}

		return null;
	}

	public Long middleTiket(final ForecastCategoryEnum forecastCategory) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("OpportunityORM.middleTiket", Long.class);
			query.setParameter("forecastCategory", forecastCategory);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method middleTiket");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method middleTiket");
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SellerXSalesVO> sellerXSales(final int year) {
		StringBuilder sql = new StringBuilder();
		List<Object[]> result;
		List<SellerXSalesVO> sellerXSales = new ArrayList<SellerXSalesVO>();

		sql.append("SELECT ");
		sql.append("	COUNT(o.id) as QTD ");
		sql.append("	,CONCAT(p.name, ' ', p.lastName) as SELLER ");
		sql.append("	,SUM(o.amount) AMOUNT ");
		sql.append("	,MONTH(o.closeDate) as MONTH ");
		sql.append("FROM ");
		sql.append("	tb_opportunity as o ");
		sql.append("INNER JOIN ");
		sql.append("	tb_person as p on o.id_person = p.id ");
		sql.append("INNER JOIN ");
		sql.append("	tb_user as u on p.id_user = u.id ");
		sql.append("WHERE ");
		sql.append("	o.status = 1 ");
		sql.append("AND ");
		sql.append("	u.status = 1 ");
		sql.append("AND ");
		sql.append("	o.forecastCategory = 4 ");
		sql.append("AND ");
		sql.append("	YEAR(o.closeDate) = " + year + " ");
		sql.append("GROUP BY ");
		sql.append("	CONCAT(p.name, ' ', p.lastName) ");
		sql.append("	,MONTH(o.closeDate) ");
		sql.append("ORDER BY ");
		sql.append("	MONTH(o.closeDate) ");

		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			result = query.getResultList();

			for (Object[] i : result) {
				Long id = Long.valueOf(i[0].toString());
				String seller = i[1].toString();
				String amount = i[2].toString();
				int month = Integer.valueOf(i[3].toString());

				sellerXSales.add(new SellerXSalesVO(id, seller, amount, month));
			}

			return sellerXSales;
		} catch (NoResultException e) {
			log.severe("Error on method sellerXSales");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method sellerXSales");
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SellerCompensationVO> sellerCompensation(final int findYear, final int findMonth, final Long idPerson) {
		StringBuilder sql = new StringBuilder();
		List<Object[]> result;
		List<SellerCompensationVO> sellerCompensation = new ArrayList<SellerCompensationVO>();

		sql.append("SELECT ");
		sql.append("	COUNT(o.id) as QTD ");
		sql.append("	,CONCAT(p.name, ' ', p.lastName) as SELLER ");
		sql.append("	,SUM(o.amount) AMOUNT ");
		sql.append("	,MONTH(o.closeDate) as MONTH ");
		sql.append("FROM ");
		sql.append("	tb_opportunity as o ");
		sql.append("INNER JOIN ");
		sql.append("	tb_person as p on o.id_person = p.id ");
		sql.append("INNER JOIN ");
		sql.append("	tb_user as u on p.id_user = u.id ");
		sql.append("WHERE ");
		sql.append("	o.status = 1 ");
		sql.append("AND ");
		sql.append("	u.status = 1 ");
		sql.append("AND ");
		sql.append("	p.id = " + idPerson + " ");
		sql.append("AND ");
		sql.append("	o.forecastCategory = 4 ");
		sql.append("AND ");
		sql.append("	MONTH(o.closeDate) = " + findMonth + " ");
		sql.append("AND ");
		sql.append("	YEAR(o.closeDate) = " + findYear + " ");
		sql.append("GROUP BY ");
		sql.append("	CONCAT(p.name, ' ', p.lastName) ");
		sql.append("	,MONTH(o.closeDate) ");
		sql.append("ORDER BY ");
		sql.append("	MONTH(o.closeDate) ");

		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			result = query.getResultList();

			for (Object[] i : result) {
				Long id = Long.valueOf(i[0].toString());
				String seller = i[1].toString();
				String amount = i[2].toString();
				int month = Integer.valueOf(i[3].toString());

				sellerCompensation.add(new SellerCompensationVO(id, seller, amount, month));
			}

			return sellerCompensation;
		} catch (NoResultException e) {
			log.severe("Error on method sellerCompensation");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method sellerCompensation");
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SalesAmountVO> salesAmount(final int year) {
		StringBuilder sql = new StringBuilder();
		List<Object[]> result;
		List<SalesAmountVO> salesAmount = new ArrayList<SalesAmountVO>();

		sql.append("SELECT ");
		sql.append("	COUNT(o.id) as QTD ");
		sql.append("	,SUM(o.amount) AMOUNT ");
		sql.append("	,MONTH(o.closeDate) as MONTH ");
		sql.append("FROM ");
		sql.append("	tb_opportunity as o ");
		sql.append("WHERE ");
		sql.append("	o.forecastCategory = 4 ");
		sql.append("	AND ");
		sql.append("	YEAR(o.closeDate) = " + year + " ");
		sql.append("GROUP BY ");
		sql.append("	MONTH(o.closeDate) ");
		sql.append("ORDER BY ");
		sql.append("	MONTH(o.closeDate) ");

		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			result = query.getResultList();

			for (Object[] i : result) {
				Long id = Long.valueOf(i[0].toString());
				String amount = i[1].toString();
				int month = Integer.valueOf(i[2].toString());

				salesAmount.add(new SalesAmountVO(id, amount, month));
			}

			return salesAmount;
		} catch (NoResultException e) {
			log.severe("Error on method salesAmount");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method salesAmount");
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PendingForecastCategoryVO> pendingForecastCategory() {
		StringBuilder sql = new StringBuilder();
		List<Object[]> result;
		List<PendingForecastCategoryVO> pendingForecastCategory = new ArrayList<PendingForecastCategoryVO>();

		sql.append("SELECT ");
		sql.append("	forecastCategory ");
		sql.append("	,count(id) AS QTD ");
		sql.append("	,DATEDIFF(now(), inclusion) AS PENDING ");
		sql.append("	,count(forecastCategory) ");
		sql.append("FROM ");
		sql.append("	tb_opportunity as o ");
		sql.append("WHERE ");
		sql.append("	o.status = 1 ");
		sql.append("	AND ");
		sql.append("	o.forecastCategory <> 4 ");
		sql.append("GROUP BY ");
		sql.append("	forecastCategory ");
		sql.append("	,DATEDIFF(now(), inclusion)");

		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			result = query.getResultList();

			for (Object[] i : result) {
				ForecastCategoryEnum forecastCategory = ForecastCategoryEnum
						.valueOfId(Integer.valueOf(i[0].toString()));
				Integer qtd = Integer.valueOf(i[1].toString());
				Integer pending = Integer.valueOf(i[2].toString());
				Integer size = Integer.valueOf(i[3].toString());

				pendingForecastCategory.add(new PendingForecastCategoryVO(forecastCategory, qtd, pending, size));
			}

			return pendingForecastCategory;
		} catch (NoResultException e) {
			log.severe("Error on method pendingForecastCategory");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method pendingForecastCategory");
			e.printStackTrace();
		}

		return null;
	}

	public OpportunityORM findById(final long id) {
		return entityManager.find(OpportunityORM.class, id);
	}

	public void save(final OpportunityORM orm) {
		entityManager.persist(orm);
	}

	public void update(final OpportunityORM orm) {
		entityManager.merge(orm);
	}
}