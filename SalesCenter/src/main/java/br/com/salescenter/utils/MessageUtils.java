package br.com.salescenter.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageUtils {

	private static void message(Severity severity, String prefix, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, prefix, message));
	}

	public static String recoverString(final String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "m");
		return bundle.getString(key);
	}

	public static String recoverString(final String key, final Object params[]) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "m");

		if (params != null) { return MessageFormat.format(bundle.getString(key), params); }

		return bundle.getString(key);
	}

	public static void messageWarning(final String message) {
		message(FacesMessage.SEVERITY_WARN, recoverString("title.attention"), message);
	}

	public static void messageInfo(final String message) {
		message(FacesMessage.SEVERITY_INFO, recoverString("title.information"), message);
	}

	public static void messageError(final String message) {
		message(FacesMessage.SEVERITY_ERROR, recoverString("title.error"), message);
	}
}