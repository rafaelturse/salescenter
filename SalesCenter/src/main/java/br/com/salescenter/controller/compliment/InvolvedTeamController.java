package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.orm.compliment.InvolvedTeamORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "involvedTeamController")
@ViewScoped
public class InvolvedTeamController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private InvolvedTeamORM orm;
	private List<InvolvedTeamORM> orms;

	@PostConstruct
	public void init() {
		orm = new InvolvedTeamORM();
		orms = new ArrayList<InvolvedTeamORM>();
	}

	public String reinit() {
		orm = new InvolvedTeamORM();
		return null;
	}
}
