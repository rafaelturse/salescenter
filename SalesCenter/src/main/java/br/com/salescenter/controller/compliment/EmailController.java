package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.orm.compliment.EmailORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "emailController")
@ViewScoped
public class EmailController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EmailORM orm;
	private List<EmailORM> orms;

	@PostConstruct
	public void init() {
		orm = new EmailORM();
		orms = new ArrayList<EmailORM>();
	}

	public String reinit() {
		orm = new EmailORM();
		return null;
	}
}
