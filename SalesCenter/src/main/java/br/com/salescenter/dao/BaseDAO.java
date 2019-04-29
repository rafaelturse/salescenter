package br.com.salescenter.dao;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.java.Log;

@Log
public class BaseDAO {

	protected EntityManager entityManager;

	public static EntityManager jpaEntityManager() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

			return (EntityManager) request.getAttribute("entityManager");
		} catch (Exception e) {
			log.severe("Error on method jpaEntityManager");
			e.printStackTrace();
		}

		return null;
	}

	public BaseDAO() {
		entityManager = jpaEntityManager();
	}
}
