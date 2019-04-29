package br.com.salescenter.controller.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.PhoneTypeEnum;
import br.com.salescenter.contants.enumeration.PositionEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.controller.compliment.AddressController;
import br.com.salescenter.controller.compliment.PhoneController;
import br.com.salescenter.controller.login.LoginController;
import br.com.salescenter.orm.compliment.AddressORM;
import br.com.salescenter.orm.compliment.PhoneORM;
import br.com.salescenter.orm.permission.UserGroupORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.orm.user.UserORM;
import br.com.salescenter.service.PersonService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.utils.TextUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertUserController")
@RequestScoped
@Log
public class InsertUserController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PersonORM orm;

	@Inject
	PhoneController phoneController;

	@Inject
	AddressController addressController;

	@Inject
	LoginController loginController;

	@Inject
	PersonService personService;

	private GenderEnum[] genders = GenderEnum.values();
	private PersonORM topUser;
	private PhoneTypeEnum phoneType = PhoneTypeEnum.valueOfId(1);
	private PhoneTypeEnum[] phoneTypes = PhoneTypeEnum.values();
	private PositionEnum[] positions = PositionEnum.values();
	private StatusEnum[] status = StatusEnum.values();
	private UserGroupORM userGroup;

	@PostConstruct
	public void init() {
		orm.setUser(new UserORM());
	}

	private boolean validate() {
		if (!ValidationUtils.existsLogin(loginController, orm.getUser().getLogin())) { return false; }
		if (!ValidationUtils.existsRG(personService, orm.getRg())) { return false; }
		if (!ValidationUtils.existsCPF(personService, orm.getCpf())) { return false; }
		if (!ValidationUtils.existsEmail(personService, orm.getEmail())) { return false; }

		return true;
	}

	private boolean setPhones() {
		try {
			for (PhoneORM i : phoneController.getOrms()) {
				i.setPerson(orm);
			}

			orm.setPhones(phoneController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setPhones");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setAddresses() {
		try {
			for (AddressORM i : addressController.getOrms()) {
				i.setPerson(orm);
			}

			orm.setAddresses(addressController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setAddresses");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setDefaultData() {
		try {
			orm.getUser().setEmployeeNumber(ValidationUtils.existsEmployeeNumber(loginController));
			orm.getUser().setTheme(SessionUtils.recoverDefaultTheme());
			orm.getUser().setLanguage(SessionUtils.recoverDefaultLanguage());
			orm.getUser().setUserGroup(userGroup);

			orm.setTopUser(topUser);

			orm.setInclusion(DateTimeUtils.today());
			orm.setLastUpdate(DateTimeUtils.today());

			return true;
		} catch (Exception e) {
			log.severe("Error on method setDefaultData");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setCompliments() {
		if (!setPhones()) { return false; } ;
		if (!setAddresses()) { return false; } ;
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean prepareToSave() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	private void reinitFields() {
		orm = new PersonORM();
		phoneController.init();
		addressController.init();
		orm.setUser(new UserORM());
		topUser = new PersonORM();
		userGroup = new UserGroupORM();
	}

	public String cancel() {
		return "search-user";
	}

	/**
	 * configura enum de telefone correto, para configurar a mascara
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void setPhoneType(AjaxBehaviorEvent event) throws Exception {
		phoneType = PhoneTypeEnum.valueOfDescription(
				TextUtils.firstUpper(event.getComponent().getAttributes().get("value").toString().trim()));
	}

	public List<PersonORM> searchTopUser(String value) {
		return personService.searchTopUser(value);
	}

	public List<PersonORM> searchPerson(String value) {
		return personService.searchPerson(value);
	}

	public String findById(final long id) {
		PersonORM person = personService.findById(id);

		return person.getName() + " " + person.getLastName();
	}

	public boolean save() {
		if (prepareToSave()) {
			personService.save(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}
