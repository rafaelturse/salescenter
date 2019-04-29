package br.com.salescenter.controller.permission;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.orm.permission.PermissionORM;
import br.com.salescenter.orm.user.UserORM;
import br.com.salescenter.service.UserService;
import br.com.salescenter.utils.SessionUtils;

@Named(value = "showHideController")
@RequestScoped
public class ShowHideController {

	@Inject
	private UserService userService;

	private List<PermissionORM> permissions() {
		UserORM i = userService.findById(SessionUtils.recoverAuthenticatedUser().getUser().getId());

		return i.getUserGroup().getPermissions();
	}

	public boolean showHide(final int screen, final String key) {
		List<PermissionORM> list = permissions();

		for (PermissionORM i : list) {
			if (i.getScreen().getId() == ScreenEnum.valueOfId(screen).getId()) {
				if (key.equals("create")) { return i.getCreate() ? true : false; }
				if (key.equals("delete")) { return i.getDelete() ? true : false; }
				if (key.equals("disable")) { return i.getDisable() ? true : false; }
				if (key.equals("enable")) { return i.getEnable() ? true : false; }
				if (key.equals("read")) { return i.getRead() ? true : false; }
				if (key.equals("update")) { return i.getUpdate() ? true : false; }
			}
		}

		return false;
	}
}
