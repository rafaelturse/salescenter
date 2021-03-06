package br.com.salescenter.contants.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.salescenter.dao.UserGroupDAO;
import br.com.salescenter.orm.permission.UserGroupORM;

@FacesConverter(value = "userGroupConverter")
public class UserGroupConverter implements Converter {

	List<UserGroupORM> userGroup = new UserGroupDAO().listAll();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.trim().equals("")) {
			return null;
		} else {
			int number = Integer.parseInt(value);

			for (UserGroupORM i : userGroup) {
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
			return String.valueOf(((UserGroupORM) value).getId());
		}
	}
}