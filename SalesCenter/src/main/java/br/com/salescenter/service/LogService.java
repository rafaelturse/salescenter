package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.dao.LogDAO;
import br.com.salescenter.orm.permission.LogORM;

@RequestScoped
public class LogService {

	@Inject
	private LogDAO logDAO;

	public void save(final LogORM orm) {
		logDAO.save(orm);
	}

	public List<LogORM> listAll(final ScreenEnum screen, final long reference) {
		return logDAO.listAll(screen, reference);
	}
}
