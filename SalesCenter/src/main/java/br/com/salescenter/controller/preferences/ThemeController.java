package br.com.salescenter.controller.preferences;

import java.io.Serializable;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.ThemeService;
import br.com.salescenter.service.UserService;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ThemeController implements Serializable {

	UserService userService = new UserService();

	private static final long serialVersionUID = 1L;

	private TreeMap<String, String> themes;
	private ThemeService service = new ThemeService();
	private String theme;

	public ThemeController() {
		theme = service.getTheme();

		themes = new TreeMap<String, String>();
		themes.put("Afterdark", "afterdark");
		themes.put("Afternoon", "afternoon");
		themes.put("Afterwork", "afterwork");
		themes.put("Aristo", "aristo");
		themes.put("Black-Tie", "black-tie");
		themes.put("Blitzer", "blitzer");
		themes.put("Bluesky", "bluesky");
		themes.put("Bootstrap", "bootstrap");
		themes.put("Casablanca", "casablanca");
		themes.put("Cupertino", "cupertino");
		themes.put("Cruze", "cruze");
		themes.put("Dark-Hive", "dark-hive");
		themes.put("Dot-Luv", "dot-luv");
		themes.put("Eggplant", "eggplant");
		themes.put("Excite-Bike", "excite-bike");
		themes.put("Flick", "flick");
		themes.put("Glass-X", "glass-x");
		themes.put("Home", "home");
		themes.put("Hot-Sneaks", "hot-sneaks");
		themes.put("Humanity", "humanity");
		themes.put("Le-Frog", "le-frog");
		themes.put("Midnight", "midnight");
		themes.put("Mint-Choc", "mint-choc");
		themes.put("Overcast", "overcast");
		themes.put("Pepper-Grinder", "pepper-grinder");
		themes.put("Redmond", "redmond");
		themes.put("Rocket", "rocket");
		themes.put("Sam", "sam");
		themes.put("Smoothness", "smoothness");
		themes.put("South-Street", "south-street");
		themes.put("Start", "start");
		themes.put("Sunny", "sunny");
		themes.put("Swanky-Purse", "swanky-purse");
		themes.put("Trontastic", "trontastic");
		themes.put("UI-Darkness", "ui-darkness");
		themes.put("UI-Lightness", "ui-lightness");
		themes.put("Vader", "vader");
	}

	public void save() {
		service.setTheme(theme);
	}

	public void update() {
		userService.updateTheme(SessionUtils.recoverCurrentTheme(),
				SessionUtils.recoverAuthenticatedUser().getUser().getId());

		SessionUtils.updateAuthenticatedUserTheme();

		MessageUtils.messageInfo(BaseCRUD.translateEnumValue("message.saveTheme"));
	}
}
