package br.com.salescenter.utils;

import javax.faces.context.FacesContext;

import br.com.salescenter.controller.opportunity.ViewEditOpportunityController;
import br.com.salescenter.controller.preferences.LanguageController;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.service.ThemeService;

public class SessionUtils {

	public static void setAuthenticatedUser(String key, PersonORM orm) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, orm);
		setLoginDateTime();
	}

	public static void setLoginDateTime() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentDateTime",
				DateTimeUtils.now());
	}

	public static void setHashNumber(String key, Long id) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, id);
	}

	public static Long recoverHashNumber() {
		return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hashNumber");
	}

	public static PersonORM recoverAuthenticatedUser() {
		return (PersonORM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authenticatedUser");
	}

	public static String recoverCurrentTheme() {
		return ((ThemeService) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("themeService")).getCurrentTheme();
	}

	public static String recoverDefaultTheme() {
		return ((ThemeService) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("themeService")).getDEFAULT_THEME();
	}

	public static String recoverDefaultLanguage() {
		return ((LanguageController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("languageController")).getDEFAULT_LANGUAGE();
	}

	public static String recoverLoginDateTime() {
		return ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentDateTime"));
	}

	public static OpportunityORM recoverCurrentOpportunity() {
		return ((ViewEditOpportunityController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("viewEditOpportunityController")).getOrm();
	}

	public static void setResultSize(String name, int size) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, size);
	}

	public static int recoverResultSize() {
		return (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchUserResultSize");
	}

	public static Long findByParameter(String parameter) {
		return Long.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter));
	}

	public static void invalidateSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public static void updateAuthenticatedUserTheme() {
		PersonORM orm = recoverAuthenticatedUser();
		orm.getUser().setTheme(recoverCurrentTheme());
		setAuthenticatedUser("authenticatedUser", orm);
	}

	public static void updateAuthenticatedUserLanguage(final String language) {
		PersonORM orm = recoverAuthenticatedUser();
		orm.getUser().setLanguage(language);
		setAuthenticatedUser("authenticatedUser", orm);
	}
}
