package br.com.salescenter.controller.email;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "sendEmailController")
@RequestScoped
@Log
public class SendEmailController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String HOST_NAME = "smtp.gmail.com";
	private final String HOST_USER = "leal@gmail.com";
	private final int HOST_PORT = 465;
	private final String HOST_PASSWORD = "";

	private String senderEmail;
	private String senderName;
	private String subject;
	private String message;
	private String company;
	private String phone;

	private String indexMessage() {
		StringBuilder text = new StringBuilder();

		text.append("[Name: " + senderName + "]");
		text.append(" - ");
		text.append("[Email: " + senderEmail + "]");
		text.append(" - ");
		text.append("[Subject: " + subject + "]");
		text.append(" - ");
		text.append("[Phone: " + phone + "]");
		text.append(" - ");
		text.append("[Company: " + company + "]");
		text.append(" - ");
		text.append("[Message: " + message + "]");

		return text.toString();
	}

	private void sendHTMLEmail(final String senderEmail, final String senderName, final String subject) {
		HtmlEmail email = new HtmlEmail();

		try {
			email.setSSLOnConnect(false);

			email.setHostName(HOST_NAME);
			email.setSmtpPort(HOST_PORT);
			email.setAuthenticator(new DefaultAuthenticator(HOST_USER, HOST_PASSWORD));
			email.setSSLOnConnect(true);

			email.setFrom(senderEmail, senderName);

			email.setDebug(true);

			email.setSubject(subject);
			email.setHtmlMsg(indexMessage());

			email.addTo(HOST_USER);

			email.send();
		} catch (EmailException e) {
			log.severe("Error on method sendHTMLEmail");
			e.printStackTrace();
		}
	}

	public boolean validate() {
		if (ValidationUtils.isEmpty(senderName, "requiered.name")) { return false; }
		if (ValidationUtils.isEmpty(senderEmail, "requiered.email")) { return false; }
		if (ValidationUtils.isEmpty(phone, "requiered.phoneNumber")) { return false; }
		if (ValidationUtils.isEmpty(company, "requiered.company")) { return false; }
		if (ValidationUtils.isEmpty(subject, "requiered.subject")) { return false; }
		if (ValidationUtils.isEmpty(message, "requiered.message")) { return false; }

		return true;
	}

	public void sendIndexContactEmail() {
		if (validate()) {
			sendHTMLEmail(senderEmail, senderName, "INDEX_PAGE_CONTACT");

			MessageUtils.messageInfo(translateEnumValue("message.sendEmail"));
		}
	}

}
