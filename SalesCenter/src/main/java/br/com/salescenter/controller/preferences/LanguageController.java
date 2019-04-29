package br.com.salescenter.controller.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.UserService;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "languageController")
@SessionScoped
public class LanguageController {

	UserService userService = new UserService();

	private final String EN = "en";
	private final String BR = "pt";
	private final String DEFAULT_LANGUAGE = BR;

	private String language = BR;
	private List<Locale> locales = new ArrayList<Locale>();

	@PostConstruct
	public void init() {
		locales.add(new Locale(EN));
		locales.add(new Locale(BR));
	}

	public void setLanguageByList(AjaxBehaviorEvent event) throws Exception {
		String value = event.getComponent().getAttributes().get("value").toString().trim();
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(value));
		language = value;
	}

	public void update() {
		userService.updateLanguage(language, SessionUtils.recoverAuthenticatedUser().getUser().getId());
		
		SessionUtils.updateAuthenticatedUserLanguage(language);

		MessageUtils.messageInfo(BaseCRUD.translateEnumValue("message.saveLanguage"));
	}
	
	public void recoverUserLanguage() {
		language = SessionUtils.recoverAuthenticatedUser().getUser().getLanguage();
	}
}