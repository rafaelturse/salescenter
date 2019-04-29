package br.com.salescenter.contants.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("phoneConverter")
public class PhoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		if (valor != null || valor != "") {
			valor = valor.toString().replaceAll("[- /' ']", "");
			valor = valor.toString().replaceAll("[-()]", "");
		}
		
		return valor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object valor) {
		return valor.toString();
	}
}