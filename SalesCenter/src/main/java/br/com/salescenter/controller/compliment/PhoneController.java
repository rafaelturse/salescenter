package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.PhoneTypeEnum;
import br.com.salescenter.orm.compliment.PhoneORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "phoneController")
@ViewScoped
public class PhoneController implements Serializable {

	private static final long serialVersionUID = 1L;

	private PhoneTypeEnum[] phoneTypes = PhoneTypeEnum.values();
	
	private PhoneORM orm;
	private List<PhoneORM> orms;

	@PostConstruct
	public void init() {
		orm = new PhoneORM();
		orms = new ArrayList<PhoneORM>();
	}

	public String reinit() {
		orm = new PhoneORM();
		return null;
	}
}
