package br.com.salescenter.contants.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("cnpjConverter")
public class CNPJConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cnpj = value;

		if (value != null && !value.equals(""))
			cnpj = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");

		return cnpj;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cnpj = (String) value;

		if (cnpj != null && cnpj.length() == 14)
			cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
					+ cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);

		return cnpj;
	}
}