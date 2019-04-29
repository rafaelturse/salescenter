package br.com.salescenter.controller.sale;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.sale.CompensationORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.service.CompensationService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertCompensationController")
@RequestScoped
@Log
public class InsertCompensationController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String SEARCH = "search-compensation";

	@Inject
	CompensationORM orm;

	@Inject
	CompensationService compensationService;

	private StatusEnum[] status = StatusEnum.values();

	private PersonORM seller;

	@PostConstruct
	public void init() {
		orm.setSeller(new PersonORM());

		orm.setStatus(StatusEnum.ATIVO);
	}

	private void emptyValues() {
		if ((orm.getJanuary() == null) || (orm.getJanuary() < 0)) {
			orm.setJanuary(0.0);
		}

		if ((orm.getFebruary() == null) || (orm.getFebruary() < 0)) {
			orm.setFebruary(0.0);
		}

		if ((orm.getMarch() == null) || (orm.getMarch() < 0)) {
			orm.setMarch(0.0);
		}

		if ((orm.getApril() == null) || (orm.getApril() < 0)) {
			orm.setApril(0.0);
		}

		if ((orm.getMay() == null) || (orm.getMay() < 0)) {
			orm.setMay(0.0);
		}

		if ((orm.getJune() == null) || (orm.getJune() < 0)) {
			orm.setJune(0.0);
		}

		if ((orm.getJuly() == null) || (orm.getJuly() < 0)) {
			orm.setJuly(0.0);
		}

		if ((orm.getAugust() == null) || (orm.getJanuary() < 0)) {
			orm.setAugust(0.0);
		}

		if ((orm.getSeptember() == null) || (orm.getSeptember() < 0)) {
			orm.setSeptember(0.0);
		}

		if ((orm.getOctober() == null) || (orm.getOctober() < 0)) {
			orm.setOctober(0.0);
		}

		if ((orm.getNovember() == null) || (orm.getNovember() < 0)) {
			orm.setNovember(0.0);
		}

		if ((orm.getDecember() == null) || (orm.getDecember() < 0)) {
			orm.setDecember(0.0);
		}
	}

	private boolean setDefaultData() {
		try {
			orm.setSeller(seller);

			orm.setInclusion(DateTimeUtils.today());
			orm.setLastUpdate(DateTimeUtils.today());

			emptyValues();

			return true;
		} catch (Exception e) {
			log.severe("Error on method setDefaultData");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setCompliments() {
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean prepareToSave() {
		if (!setCompliments()) { return false; }

		return true;
	}

	public String cancel() {
		return SEARCH;
	}

	private void reinitFields() {
		orm = new CompensationORM();
		seller = new PersonORM();
	}

	public boolean save() {
		if (prepareToSave()) {
			compensationService.save(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}