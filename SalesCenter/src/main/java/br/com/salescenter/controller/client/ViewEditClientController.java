package br.com.salescenter.controller.client;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.PhoneTypeEnum;
import br.com.salescenter.contants.enumeration.PositionEnum;
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
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.utils.TextUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "viewEditClientController")
@ManagedBean
@ViewScoped
@Log
public class ViewEditClientController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private ClientORM orm;
	//
	private boolean renderedAddressView = true;
	private boolean renderedContactView = true;
	private boolean renderedEmailView = true;
	private boolean renderedRepresentativeView = true;
	private boolean renderedSegmentView = true;
	private PhoneTypeEnum phoneType = PhoneTypeEnum.valueOfId(1);
	private PhoneTypeEnum[] phoneTypes = PhoneTypeEnum.values();
	private PositionEnum[] positions = PositionEnum.values();
	private StatusEnum[] status = StatusEnum.values();

	@PostConstruct
	public void init() {
		orm = clientService.findById(SessionUtils.findByParameter("viewId"));

		setORMs();
	}

	private void setORMs() {
		addressController.setOrms(orm.getAddresses());
		emailController.setOrms(orm.getEmails());
		phoneController.setOrms(orm.getPhones());
		representativeController.setOrms(orm.getRepresentatives());
		segmentController.setOrms(orm.getSegments());
		segmentController.initSelectedSegments();
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
		if (!setEmails()) { return false; } ;
		if (!setPhones()) { return false; } ;
		if (!setRepresentatives()) { return false; } ;
		if (!setSegments()) { return false; } ;
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean validate() {
		if (!ValidationUtils.existsSocial(clientService, orm.getId(), orm.getSocial())) { return false; }
		if (!ValidationUtils.existsFantasy(clientService, orm.getId(), orm.getFantasy())) { return false; }
		if (!ValidationUtils.existsCNPJ(clientService, orm.getId(), orm.getCnpj())) { return false; }

		return true;
	}

	private boolean prepareToUpdate() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	public String cancel() {
		return "search-client";
	}

	public String openingFormated() {
		return DateTimeUtils.formatDate(orm.getOpening());
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
			clientService.update(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));
		}

		return "search-client";
	}
}
