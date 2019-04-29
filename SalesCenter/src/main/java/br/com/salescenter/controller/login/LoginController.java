package br.com.salescenter.controller.login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.user.UserORM;
import br.com.salescenter.service.PersonService;
import br.com.salescenter.service.UserService;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "loginController")
@SessionScoped
public class LoginController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	@Inject
	private PersonService personService;

	private String login;
	private String password;
	private boolean runInitProcedures;

	private void setAuthenticatedUser(UserORM orm) {
		SessionUtils.setAuthenticatedUser("authenticatedUser", personService.findByIdUser(orm.getId()));
	}

	public String logout() {
		SessionUtils.invalidateSession();
		return "logout";
	}

	public String login() {
		UserORM orm;

		if (!ValidationUtils.validateLoginAndPassword(login, password)) { return null; }

		orm = userService.findByLoginAndPassword(login, password);

		if (orm != null) {
			setAuthenticatedUser(orm);
			runInitProcedures = true;
			return "home";
		} else {
			MessageUtils.messageError(translateEnumValue("message.notfoundUser"));
			return null;
		}
	}
	
	public void welcome() {
		runInitProcedures = false;
		
		String[] messageParams = {SessionUtils.recoverAuthenticatedUser().getName()};
		MessageUtils.messageInfo(translateEnumValue("message.welcome", messageParams));
	}
}
