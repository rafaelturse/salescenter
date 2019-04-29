package br.com.salescenter.contants.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("rgConverter")
public class RGConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String rg = value;

		if (value != null && !value.equals("")) rg = value.replaceAll("\\.", "").replaceAll("\\-", "");

		return rg;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String rg = (String) value;

		if (rg != null && rg.length() == 9)
			rg = rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8, 9);

		return rg;
	}
}