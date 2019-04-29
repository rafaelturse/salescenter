package br.com.salescenter.controller.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "loadInfoController")
@RequestScoped
public class LoadInfoController implements Serializable {

	private static final long serialVersionUID = 1L;

	PersonORM authenticatedUser;
	String currentDateTime;

	@PostConstruct
	public void init() {
		authenticatedUser = SessionUtils.recoverAuthenticatedUser(); 
		currentDateTime =  SessionUtils.recoverLoginDateTime();
	}
}
