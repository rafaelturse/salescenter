package br.com.salescenter.contants.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.salescenter.dao.ClientDAO;
import br.com.salescenter.orm.client.ClientORM;

@FacesConverter(value = "clientConverter")
public class ClientConverter implements Converter {

	List<ClientORM> client = new ClientDAO().listAll();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			int number = Integer.parseInt(value);
			
			for (ClientORM i : client) {
				if (i.getId() == number) { return i; }
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((ClientORM) value).getId());
		}
	}
}