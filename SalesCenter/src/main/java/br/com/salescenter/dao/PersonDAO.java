package br.com.salescenter.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.vo.user.FilterPersonVO;
import br.com.salescenter.vo.user.PersonVO;
import lombok.extern.java.Log;

@Log
public class PersonDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	private Criteria createCriteriaForFilter() {
		return entityManager.unwrap(Session.class).createCriteria(PersonORM.class);
	}

	public int rowFiltered() {
		Criteria criteria = createCriteriaForFilter();

		criteria.setProjection(Projections.rowCount());

		return criteria.uniqueResult() == null ? 0 : ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<PersonVO> filtered(FilterPersonVO filter) {
		Criteria criteria = createCriteriaForFilter();

		criteria.setFirstResult(filter.getFirst());
		criteria.setMaxResults(filter.getQuantity());

		criteria.setFetchMode("user", FetchMode.JOIN);
		criteria.createAlias("user", "user");

		if (StringUtils.isNotEmpty(filter.getName())) {
			criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getLastName())) {
			criteria.add(Restrictions.ilike("lastName", filter.getLastName(), MatchMode.ANYWHERE));
		}

		if (filter.getGender() != null) {
			criteria.add(Restrictions.eq("gender", filter.getGender()));
		}

		if (StringUtils.isNotEmpty(filter.getRg())) {
			criteria.add(Restrictions.ilike("rg", filter.getRg(), MatchMode.START));
		}

		if (StringUtils.isNotEmpty(filter.getCpf())) {
			criteria.add(Restrictions.ilike("cpf", filter.getCpf(), MatchMode.START));
		}

		if (StringUtils.isNotEmpty(filter.getEmail())) {
			criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(filter.getLogin())) {
			criteria.add(Restrictions.ilike("user.login", filter.getLogin(), MatchMode.ANYWHERE));
		}

		if (filter.getPosition() != null) {
			criteria.add(Restrictions.eq("user.position", filter.getPosition()));
		}

		if (StringUtils.isNotEmpty(filter.getEmployeeNumber())) {
			criteria.add(Restrictions.ilike("user.employeeNumber", filter.getEmployeeNumber(), MatchMode.START));
		}

		if (filter.getStatus() != null) {
			criteria.add(Restrictions.eq("user.status", filter.getStatus()));
		}

		criteria.add(Restrictions.between("inclusion", filter.getFrom(), filter.getTo()));

		if (filter.isAscending() && filter.getOrder() != null) {
			criteria.addOrder(Order.asc(filter.getOrder()));
		} else if (filter.getOrder() != null) {
			criteria.addOrder(Order.desc(filter.getOrder()));
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<PersonORM> searchTopUser(final String value) {
		Criteria criteria = createCriteriaForFilter();

		if (StringUtils.isNotBlank(value)) {
			criteria.add(Restrictions.ilike("name", value, MatchMode.ANYWHERE));
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<PersonORM> searchPerson(final String value) {
		Criteria criteria = createCriteriaForFilter();

		if (StringUtils.isNotBlank(value)) {
			criteria.add(Restrictions.ilike("name", value, MatchMode.ANYWHERE));
		}

		return criteria.list();
	}

	public StatusEnum updateStatus(final long id) {
		PersonORM person = findById(id);
		StatusEnum status = person.getUser().getStatus() == StatusEnum.ATIVO ? StatusEnum.INATIVO : StatusEnum.ATIVO;

		try {
			Query query = entityManager.createNamedQuery("PersonORM.updateStatus");
			query.setParameter("id", id);
			query.setParameter("status", status);

			query.executeUpdate();

			return status;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method updateStatus");
			e.printStackTrace();
		}

		return null;
	}

	public boolean existsRG(final String rg) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsRG", Long.class);
			query.setParameter("rg", rg);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsRG");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsRG(final long id, final String rg) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsIDAndRG", Long.class);
			query.setParameter("id", id);
			query.setParameter("rg", rg);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsRG");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsCPF(final String cpf) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsCPF", Long.class);
			query.setParameter("cpf", cpf);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsCPF");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsCPF(final long id, final String cpf) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsIDAndCPF", Long.class);
			query.setParameter("id", id);
			query.setParameter("cpf", cpf);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsCPF");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsEmail(final String email) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsEmail", Long.class);
			query.setParameter("email", email);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsEmail");
			e.printStackTrace();
		}

		return false;
	}

	public boolean existsEmail(final long id, final String email) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.existsIDAndEmail", Long.class);
			query.setParameter("id", id);
			query.setParameter("email", email);

			Long number = query.getSingleResult();
			return number > 0;
		} catch (Exception e) {
			log.severe("Error on method existsEmail");
			e.printStackTrace();
		}

		return false;
	}

	public PersonORM findById(final long id) {
		return entityManager.find(PersonORM.class, id);
	}

	public PersonORM findByIdUser(final Long id) {
		try {
			TypedQuery<PersonORM> query = entityManager.createNamedQuery("PersonORM.findByIdUser", PersonORM.class);
			query.setParameter("id", id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method findByIdUser");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method findByIdUser");
			e.printStackTrace();
		}

		return null;
	}

	public List<PersonORM> listAll() {
		try {
			TypedQuery<PersonORM> query = entityManager.createNamedQuery("PersonORM.listAll", PersonORM.class);

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

	public void save(final PersonORM orm) {
		entityManager.persist(orm.getUser());
		entityManager.persist(orm);
	}

	public void update(final PersonORM orm) {
		entityManager.merge(orm.getUser());
		entityManager.merge(orm);
	}

	public void updatePassword(final Long id, final String password) {
		PersonORM orm = entityManager.find(PersonORM.class, id);
		orm.getUser().setPassword(password);
		entityManager.merge(orm.getUser());
	}

	public Long amountPersonByGender(final GenderEnum gender) {
		try {
			TypedQuery<Long> query = entityManager.createNamedQuery("PersonORM.amountPersonByGender", Long.class);
			query.setParameter("gender", gender);

			return query.getSingleResult();
		} catch (NoResultException e) {
			log.severe("Error on method amountPersonByGender");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			log.severe("Error on method amountPersonByGender");
			e.printStackTrace();
		}

		return null;
	}
}