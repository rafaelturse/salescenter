package br.com.salescenter.contants.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("cepConverter")
public class CEPConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cep = value;

		if (value != null && !value.equals("")) cep = value.replaceAll("\\-", "");

		return cep;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cep = (String) value;

		if (cep != null && cep.length() == 8) cep = cep.substring(0, 4) + "-" + cep.substring(4, 7);

		return cep;
	}
}