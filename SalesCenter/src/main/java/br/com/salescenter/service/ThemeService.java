package br.com.salescenter.service;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ThemeService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String DEFAULT_THEME = "rocket";
	private String theme = DEFAULT_THEME;
	private String currentTheme;

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		currentTheme = ((ThemeService) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("themeService")).theme;

		if (params.containsKey("theme")) {
			theme = params.get("theme");
		}

		return theme;
	}

	public void recoverUserTheme() {
		theme = SessionUtils.recoverAuthenticatedUser().getUser().getTheme();
	}
}