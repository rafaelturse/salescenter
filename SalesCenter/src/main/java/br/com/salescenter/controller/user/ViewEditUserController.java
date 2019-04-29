package br.com.salescenter.controller.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

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
import br.com.salescenter.orm.user.PersonORM;
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
@Named(value = "viewEditUserController")
@ManagedBean
@ViewScoped
@Log
public class ViewEditUserController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AddressController addressController;

	@Inject
	PhoneController phoneController;

	@Inject
	LoginController loginController;

	@Inject
	PersonService personService;

	private Long id;
	private PersonORM orm;
	private GenderEnum[] genders = GenderEnum.values();
	private PhoneTypeEnum phoneType = PhoneTypeEnum.valueOfId(1);
	private PhoneTypeEnum[] phoneTypes = PhoneTypeEnum.values();
	private PositionEnum[] positions = PositionEnum.values();
	private StatusEnum[] status = StatusEnum.values();
	private boolean confirmPassword = false;
	private boolean renderedContactView = true;
	private boolean renderedAddressView = true;
	private String currentPassword;
	private String salvedPassword;

	@PostConstruct
	public void init() {
		orm = personService.findById(SessionUtils.findByParameter("viewId"));
		setSavedPassword();

		rgFormated();
		cpfFormated();

		phoneController.setOrms(orm.getPhones());
		addressController.setOrms(orm.getAddresses());
	}

	private void setSavedPassword() {
		salvedPassword = orm.getUser().getPassword();
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

	private boolean validate() {
		if (!ValidationUtils.existsLogin(loginController, orm.getUser().getId(),
				orm.getUser().getLogin())) { return false; }
		if (!ValidationUtils.existsRG(personService, orm.getId(), orm.getRg())) { return false; }
		if (!ValidationUtils.existsCPF(personService, orm.getId(), orm.getCpf())) { return false; }
		if (!ValidationUtils.existsEmail(personService, orm.getId(), orm.getEmail())) { return false; }

		return true;
	}

	private boolean prepareToUpdate() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	public String cancelUser() {
		return "search-user";
	}

	public String rgFormated() {
		return TextUtils.formatRG(orm.getRg());
	}

	public String cpfFormated() {
		return TextUtils.formatCPF(orm.getCpf());
	}

	public String birthFormated() {
		return DateTimeUtils.formatDate(orm.getBirth());
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

	public String update() {
		if (prepareToUpdate()) {
			personService.update(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));
		}

		return "search-user";
	}

	@SuppressWarnings("deprecation")
	public boolean changePassword() {
		if (!ValidationUtils.notEquals(currentPassword, salvedPassword, translateEnumValue("currentPasswordWrong"))) {
			RequestContext.getCurrentInstance().execute("PF('dChangePassword').show()");
			return false;
		} ;

		personService.updatePassword(orm.getId(), orm.getUser().getPassword());

		setSavedPassword();

		RequestContext.getCurrentInstance().addCallbackParam("saved", true);

		MessageUtils.messageInfo(translateEnumValue("message.passwordUpdated"));

		return true;
	}
}