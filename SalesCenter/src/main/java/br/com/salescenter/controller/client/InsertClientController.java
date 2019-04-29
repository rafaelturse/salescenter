package br.com.salescenter.controller.client;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.PhoneTypeEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.controller.compliment.AddressController;
import br.com.salescenter.controller.compliment.EmailController;
import br.com.salescenter.controller.compliment.PhoneController;
import br.com.salescenter.controller.compliment.RepresentativeController;
import br.com.salescenter.controller.compliment.SegmentController;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.compliment.AddressORM;
import br.com.salescenter.orm.compliment.EmailORM;
import br.com.salescenter.orm.compliment.PhoneORM;
import br.com.salescenter.orm.compliment.RepresentativeORM;
import br.com.salescenter.service.ClientService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.TextUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertClientController")
@RequestScoped
@Log
public class InsertClientController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ClientORM orm;

	@Inject
	AddressController addressController;

	@Inject
	EmailController emailController;

	@Inject
	PhoneController phoneController;

	@Inject
	RepresentativeController representativeController;

	@Inject
	SegmentController segmentController;

	@Inject
	ClientService clientService;

	private ClientORM client;
	private PhoneTypeEnum phoneType = PhoneTypeEnum.valueOfId(1);
	private PhoneTypeEnum[] phoneTypes = PhoneTypeEnum.values();
	private StatusEnum[] status = StatusEnum.values();

	private boolean validate() {
		if (!ValidationUtils.existsSocial(clientService, orm.getSocial())) { return false; }
		if (!ValidationUtils.existsFantasy(clientService, orm.getFantasy())) { return false; }
		if (!ValidationUtils.existsCNPJ(clientService, orm.getCnpj())) { return false; }

		return true;
	}

	private boolean setAddresses() {
		try {
			for (AddressORM i : addressController.getOrms()) {
				i.setClient(orm);
			}

			orm.setAddresses(addressController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setAddresses");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setContacts() {
		try {
			for (RepresentativeORM i : representativeController.getOrms()) {
				i.setClient(orm);
			}

			orm.setRepresentatives(representativeController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setContacts");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setEmails() {
		try {
			for (EmailORM i : emailController.getOrms()) {
				i.setClient(orm);
			}

			orm.setEmails(emailController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setEmails");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setPhones() {
		try {
			for (PhoneORM i : phoneController.getOrms()) {
				i.setClient(orm);
			}

			orm.setPhones(phoneController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setPhones");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setRepresentatives() {
		try {
			for (RepresentativeORM i : representativeController.getOrms()) {
				i.setClient(orm);
			}

			orm.setRepresentatives(representativeController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setRepresentatives");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setSegments() {
		try {
			orm.setSegments(segmentController.prepareORMs(orm));
			return true;
		} catch (Exception e) {
			log.severe("Error on method setSegments");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setDefaultData() {
		try {
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
		if (!setAddresses()) { return false; } ;
		if (!setContacts()) { return false; } ;
		if (!setEmails()) { return false; } ;
		if (!setPhones()) { return false; } ;
		if (!setRepresentatives()) { return false; } ;
		if (!setSegments()) { return false; } ;

		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean prepareToSave() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	private void reinitFields() {
		orm = new ClientORM();
		addressController.init();
		representativeController.init();
		emailController.init();
		phoneController.init();
		segmentController.init();
	}

	public String cancel() {
		return "search-client";
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

	public List<ClientORM> searchClient(String value) {
		return clientService.searchClient(value);
	}

	public boolean save() {
		if (prepareToSave()) {
			clientService.save(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}
