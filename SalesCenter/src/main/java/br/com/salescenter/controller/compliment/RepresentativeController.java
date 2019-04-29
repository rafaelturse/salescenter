package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.orm.compliment.RepresentativeORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "representativeController")
@ViewScoped
public class RepresentativeController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RepresentativeORM orm;
	private GenderEnum[] genders;
	private List<RepresentativeORM> orms;

	@PostConstruct
	public void init() {
		genders = GenderEnum.values();
		orm = new RepresentativeORM();
		orms = new ArrayList<RepresentativeORM>();
	}

	public String reinit() {
		orm = new RepresentativeORM();
		return null;
	}
}
