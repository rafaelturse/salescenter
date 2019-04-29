package br.com.salescenter.contants.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("streetNumberConveter")
public class StreetNumberConveter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String number = value;

		if (value != null && !value.equals("")) number = value.replaceAll("\\_", "");

		return number;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		return (String) value;
	}
}
