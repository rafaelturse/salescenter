package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.UploadDAO;
import br.com.salescenter.orm.compliment.UploadORM;

@RequestScoped
public class UploadService {

	@Inject
	private UploadDAO uploadDAO;
	
	public void upLoad(final UploadORM orm) {
		uploadDAO.upLoad(orm);
	}

	public List<UploadORM> findUploadedFiles(final Long opportunityId) {
		return uploadDAO.findUploadedFiles(opportunityId);
	}

	public UploadORM findById(final long id) {
		return uploadDAO.findById(id);
	}

	public void removeById(long id) {
		uploadDAO.removeById(id);
	}
}
