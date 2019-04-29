package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.salescenter.orm.compliment.ProductORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "productController")
@ViewScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductORM orm;
	private List<ProductORM> orms;

	@PostConstruct
	public void init() {
		orm = new ProductORM();
		orms = new ArrayList<ProductORM>();
	}

	public String reinit() {
		orm = new ProductORM();
		return null;
	}
}
