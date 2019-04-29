package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.StateEnum;
import br.com.salescenter.contants.enumeration.StreetTypeEnum;
import br.com.salescenter.orm.compliment.AddressORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "addressController")
@ViewScoped
public class AddressController implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreetTypeEnum[] streetTypes = StreetTypeEnum.values();
	private StateEnum[] states = StateEnum.values();
	private AddressORM orm;
	private List<AddressORM> orms;
	
	@PostConstruct
	public void init() {
		orm = new AddressORM();
		orm.setCountry("Brasil");
		
		orms = new ArrayList<AddressORM>();
	}

	public String reinit() {
		orm = new AddressORM();
		orm.setCountry("Brasil");
		
		return null;
	}
}