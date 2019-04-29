package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.contants.enumeration.CompetitorTypeEnum;
import br.com.salescenter.orm.compliment.CompetitorORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "competitorController")
@ViewScoped
public class CompetitorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private CompetitorTypeEnum[] competitorTypes = CompetitorTypeEnum.values();
	
	private CompetitorORM orm;
	private List<CompetitorORM> orms;

	@PostConstruct
	public void init() {
		orm = new CompetitorORM();
		orms = new ArrayList<CompetitorORM>();
	}

	public String reinit() {
		orm = new CompetitorORM();
		return null;
	}
}
