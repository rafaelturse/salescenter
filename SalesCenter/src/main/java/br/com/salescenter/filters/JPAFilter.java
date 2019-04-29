package br.com.salescenter.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.java.Log;

/**
 * Servlet Filter implementation class JPAFilter
 */
@WebFilter(servletNames = {"Faces Servlet"})
@Log
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;
	private String persistence_unit_name = "SalesCenter";

	public JPAFilter() {
	}

	public void destroy() {
		this.entityManagerFactory.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* CRIANDO UM ENTITYMANAGER */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/* ADICIONANDO ELE NA REQUISIÇÃO */
		request.setAttribute("entityManager", entityManager);

		/* INICIANDO UMA TRANSAÇÃO */
		entityManager.getTransaction().begin();

		/* INICIANDO FACES SERVLET */
		chain.doFilter(request, response);

		try {
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			log.severe("Error on method doFilter");
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}
}