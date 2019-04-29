package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.dao.PersonDAO;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.vo.user.FilterPersonVO;
import br.com.salescenter.vo.user.PersonVO;

@RequestScoped
public class PersonService {

	@Inject
	private PersonDAO personDAO;

	public void save(final PersonORM orm) {
		personDAO.save(orm);
	}
	
	public void update(final PersonORM orm) {
		personDAO.update(orm);
	}
	
	public void updatePassword(final Long id, final String password) {
		personDAO.updatePassword(id, password);
	}
	
	public List<PersonORM> searchTopUser(final String value) {
		return personDAO.searchTopUser(value);
	}
	
	public List<PersonORM> searchPerson(final String value) {
		return personDAO.searchPerson(value);
	}

	public int rowFiltered() {
		return personDAO.rowFiltered();
	}

	public List<PersonVO> filtered(final FilterPersonVO filter) {
		return personDAO.filtered(filter);
	}

	public StatusEnum updateStatus(final long id) {
		return personDAO.updateStatus(id);
	}

	public PersonORM findById(final long id) {
		return personDAO.findById(id);
	}
	
	public PersonORM findByIdUser(final Long id) {
		return personDAO.findByIdUser(id);
	}
	
	public boolean existsRG(final String rg) {
		return personDAO.existsRG(rg);
	}

	public boolean existsRG(final long id, final String rg) {
		return personDAO.existsRG(id, rg);
	} 
	
	public boolean existsCPF(final String cpf) {
		return personDAO.existsCPF(cpf);
	}
	
	public boolean existsCPF(final long id, final String cpf) {
		return personDAO.existsCPF(id, cpf);
	}
	
	public boolean existsEmail(final String email) {
		return personDAO.existsEmail(email);
	}
	
	public boolean existsEmail(final long id, final String email) {
		return personDAO.existsEmail(id, email);
	}
	
	public List<PersonORM> listAll(){
		return personDAO.listAll();
	}

	public Long amountPersonByGender(final GenderEnum gender) {
		return personDAO.amountPersonByGender(gender);
	}
}
