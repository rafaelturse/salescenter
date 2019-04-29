package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.HelpDAO;
import br.com.salescenter.orm.help.HelpORM;
import br.com.salescenter.vo.help.FilterHelpVO;
import br.com.salescenter.vo.help.HelpVO;

@RequestScoped
public class HelpService {

	@Inject
	private HelpDAO helpDAO;

	public void save(final HelpORM orm) {
		helpDAO.save(orm);
	}

	public void update(final HelpORM orm) {
		helpDAO.update(orm);
	}

	public HelpORM findById(final long id) {
		return helpDAO.findById(id);
	}

	public int rowFiltered() {
		return helpDAO.rowFiltered();
	}

	public List<HelpVO> filtered(final FilterHelpVO filter) {
		return helpDAO.filtered(filter);
	}
}
