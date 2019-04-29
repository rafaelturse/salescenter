package br.com.salescenter.utils;

import java.time.LocalDate;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.controller.login.LoginController;
import br.com.salescenter.service.ClientService;
import br.com.salescenter.service.PersonService;
import br.com.salescenter.service.UserGroupService;

public class ValidationUtils extends BaseCRUD {

	public static boolean validateLoginAndPassword(final String login, final String password) {
		if (StringUtils.isEmpty(login)) {
			MessageUtils.messageError(translateEnumValue("message.enterLogin"));
			return false;
		} else if (StringUtils.isBlank(password)) {
			MessageUtils.messageError(translateEnumValue("message.enterPassword"));
			return false;
		}

		return true;
	}

	public static boolean existsLogin(final LoginController userController, final String login) {
		if (userController.getUserService().existsLogin(login)) {
			String[] messageParams = {String.valueOf(login)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsLogin", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsLogin(final LoginController userController, final long id, final String login) {
		if (userController.getUserService().existsLogin(id, login)) {
			String[] messageParams = {String.valueOf(login)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsLogin", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsRG(final PersonService personService, final String rg) {
		if (personService.existsRG(rg)) {
			String[] messageParams = {String.valueOf(rg)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsRG", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsRG(final PersonService personService, final long id, final String rg) {
		if (personService.existsRG(id, rg)) {
			String[] messageParams = {String.valueOf(rg)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsRG", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsCPF(final PersonService personService, final String cpf) {
		if (personService.existsCPF(cpf)) {
			String[] messageParams = {String.valueOf(cpf)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsCPF", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsCPF(final PersonService personService, final long id, final String cpf) {
		if (personService.existsCPF(id, cpf)) {
			String[] messageParams = {String.valueOf(cpf)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsCPF", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsCNPJ(final ClientService clientService, final String cnpj) {
		if (clientService.existsCNPJ(cnpj)) {
			String[] messageParams = {String.valueOf(cnpj)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsCNPJ", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsCNPJ(final ClientService clientService, final long id, final String cnpj) {
		if (clientService.existsCNPJ(id, cnpj)) {
			String[] messageParams = {String.valueOf(cnpj)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsCNPJ", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsEmail(final PersonService personService, final String email) {
		if (personService.existsEmail(email)) {
			String[] messageParams = {String.valueOf(email)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsEmail", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsEmail(final PersonService personService, final long id, final String email) {
		if (personService.existsEmail(id, email)) {
			String[] messageParams = {String.valueOf(email)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsEmail", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsSocial(final ClientService clientService, final String social) {
		if (clientService.existsSocial(social)) {
			String[] messageParams = {String.valueOf(social)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsSocial", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsSocial(final ClientService clientService, final long id, final String social) {
		if (clientService.existsSocial(id, social)) {
			String[] messageParams = {String.valueOf(social)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsSocial", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsFantasy(final ClientService clientService, final String fantasy) {
		if (clientService.existsFantasy(fantasy)) {
			String[] messageParams = {String.valueOf(fantasy)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsFantasy", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsFantasy(final ClientService clientService, final long id, final String fantasy) {
		if (clientService.existsFantasy(id, fantasy)) {
			String[] messageParams = {String.valueOf(fantasy)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsFantasy", messageParams));
			return false;
		}

		return true;
	}

	public static String existsEmployeeNumber(final LoginController userController) {
		boolean control = true;
		String employeeNumber = "";

		while (control) {
			while (employeeNumber.length() < 6) {
				employeeNumber += String.valueOf(new Random().nextInt(9));
			}

			control = userController.getUserService().existsEmployeeNumber(employeeNumber);
		}

		return employeeNumber;
	}

	public static boolean existsUserGroupName(final UserGroupService userGroupService, final String name) {
		if (userGroupService.existsUserGroupName(name)) {
			String[] messageParams = {String.valueOf(name)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsUserGroup", messageParams));
			return false;
		}

		return true;
	}

	public static boolean existsUserGroupName(final UserGroupService userGroupService, final long id,
			final String name) {
		if (userGroupService.existsUserGroupName(id, name)) {
			String[] messageParams = {String.valueOf(name)};
			MessageUtils.messageWarning(BaseCRUD.translateEnumValue("message.existsUserGroup", messageParams));
			return false;
		}

		return true;
	}

	public static boolean coherentDates(LocalDate from, LocalDate to) {
		if (DateTimeUtils.fromAfterTo(from, to)) {
			MessageUtils.messageError(BaseCRUD.translateEnumValue("message.coherentDates"));
			return false;
		}

		return true;
	}

	public static boolean notEquals(final String text1, final String text2, final String message) {
		if (!text1.equals(text2)) {
			MessageUtils.messageError(message);
			return false;
		}

		return true;
	}

	public static boolean isEmpty(final String value) {
		return value == null || StringUtils.isEmpty(value) ? true : false;
	}

	public static boolean isEmpty(final String value, final String message) {
		if ((value == null) || (StringUtils.isEmpty(value))) {
			MessageUtils.messageError(translateEnumValue(message));
			return true;
		} else {
			return false;
		}
	}
}
