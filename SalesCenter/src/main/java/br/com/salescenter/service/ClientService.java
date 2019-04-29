package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.dao.ClientDAO;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.vo.client.ClientVO;
import br.com.salescenter.vo.client.FilterClientVO;

@RequestScoped
public class ClientService {

	@Inject
	private ClientDAO clientDAO;
	
	public List<ClientORM> searchClient(final String value) {
		return clientDAO.searchClient(value);
	}
	
	public void save(final ClientORM orm) {
		clientDAO.save(orm);
	}
	
	public void update(final ClientORM orm) {
		clientDAO.update(orm);
	}
	
	public ClientORM findById(final long id) {
		return clientDAO.findById(id);
	}
	
	public int rowFiltered() {
		return clientDAO.rowFiltered();
	}

	public List<ClientVO> filtered(final FilterClientVO filter) {
		return clientDAO.filtered(filter);
	}
	
	public boolean existsCNPJ(final String cnpj) {
		return clientDAO.existsCNPJ(cnpj);
	}

	public boolean existsCNPJ(final long id, final String cnpj) {
		return clientDAO.existsCNPJ(id, cnpj);
	}
	
	public boolean existsSocial(final String social) {
		return clientDAO.existsSocial(social);
	}

	public boolean existsSocial(final long id, final String social) {
		return clientDAO.existsSocial(id, social);
	}
	
	public boolean existsFantasy(final String fantasy) {
		return clientDAO.existsFantasy(fantasy);
	}

	public boolean existsFantasy(final long id, final String fantasy) {
		return clientDAO.existsFantasy(id, fantasy);
	}
}
