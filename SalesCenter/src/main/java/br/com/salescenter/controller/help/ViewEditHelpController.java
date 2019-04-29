package br.com.salescenter.controller.help;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.help.HelpORM;
import br.com.salescenter.service.HelpService;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "viewEditHelpController")
@ManagedBean
@ViewScoped
public class ViewEditHelpController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	HelpORM orm;

	@Inject
	HelpService helpService;

	private StatusEnum[] status = StatusEnum.values();

	@PostConstruct
	public void init() {
		orm = helpService.findById(SessionUtils.findByParameter("viewId"));
	}

	public String cancel() {
		return "search-help";
	}

	public String update() {
		helpService.update(orm);

		MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));

		return "search-help";
	}
}