package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.CompensationDAO;
import br.com.salescenter.orm.sale.CompensationORM;
import br.com.salescenter.vo.compensation.CompensationVO;
import br.com.salescenter.vo.compensation.FilterCompensationVO;

@RequestScoped
public class CompensationService {

	@Inject
	private CompensationDAO compensationDAO;

	public void save(final CompensationORM orm) {
		compensationDAO.save(orm);
	}

	public void update(final CompensationORM orm) {
		compensationDAO.update(orm);
	}

	public CompensationORM findById(final long id) {
		return compensationDAO.findById(id);
	}

	public int rowFiltered() {
		return compensationDAO.rowFiltered();
	}

	public List<CompensationVO> filtered(final FilterCompensationVO filter) {
		return compensationDAO.filtered(filter);
	}
}
