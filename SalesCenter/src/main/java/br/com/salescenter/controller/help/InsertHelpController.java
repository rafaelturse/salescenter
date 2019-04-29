package br.com.salescenter.controller.help;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.help.HelpORM;
import br.com.salescenter.service.HelpService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertHelpController")
@RequestScoped
@Log
public class InsertHelpController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	HelpORM orm;

	@Inject
	HelpService helpService;

	private StatusEnum[] status = StatusEnum.values();

	private boolean setDefaultData() {
		try {
			orm.setInclusion(DateTimeUtils.today());
			orm.setUser(SessionUtils.recoverAuthenticatedUser().getId());

			return true;
		} catch (Exception e) {
			log.severe("Error on method setDefaultData");
			e.printStackTrace();
		}

		return false;
	}

	private boolean prepareToSave() {
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private void reinitFields() {
		orm = new HelpORM();
	}

	public String cancel() {
		return "search-help";
	}

	public boolean save() {
		if (prepareToSave()) {
			helpService.save(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}