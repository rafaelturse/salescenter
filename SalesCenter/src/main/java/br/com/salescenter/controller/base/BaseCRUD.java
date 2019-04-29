package br.com.salescenter.controller.base;

import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.contants.enumeration.ActionEnum;
import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.orm.permission.LogORM;
import br.com.salescenter.service.LogService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.extern.java.Log;

@RequestScoped
@Log
public abstract class BaseCRUD {

	@Inject
	LogORM orm;

	@Inject
	LogService logService;

	public LocalDate today() {
		return DateTimeUtils.today();
	}

	public LocalDate maximumHireDate() {
		return DateTimeUtils.maximumHireDate();
	}

	public LocalDate maximumSearchDate() {
		return DateTimeUtils.maximumSearchDate();
	}

	public LocalDate minimumHireDate() {
		return DateTimeUtils.minimumHireDate();
	}

	public String rangeInYearMinimumMaximum() {
		return DateTimeUtils.rangeInYearMinimumMaximum();
	}

	public String rangeInYearSearch() {
		return DateTimeUtils.rangeInYearSearch();
	}

	public static String translateEnumValue(String value) {
		return MessageUtils.recoverString(value);
	}

	public static String translateEnumValue(String value, Object params[]) {
		return MessageUtils.recoverString(value, params);
	}

	public void createLog(final ScreenEnum screen, final long reference) {
		try {
			orm.setScreen(screen);
			orm.setReference(reference);
			orm.setInclusion(DateTimeUtils.today());
			orm.setUser(SessionUtils.recoverAuthenticatedUser().getId());
			orm.setAction(ActionEnum.CREATE);
			orm.setField("-");
			orm.setOldValue("-");
			orm.setNewValue("-");

			logService.save(orm);
		} catch (Exception e) {
			log.severe("Error on method updateLog");
			e.printStackTrace();
		}
	}

	public void updateLog(final ScreenEnum screen, final long reference, final String field, final String oldValue,
			final String newValue) {
		try {
			orm.setScreen(screen);
			orm.setReference(reference);
			orm.setInclusion(DateTimeUtils.today());
			orm.setUser(SessionUtils.recoverAuthenticatedUser().getId());
			orm.setAction(ActionEnum.UPDATE);
			orm.setField(field);
			orm.setOldValue(oldValue);
			orm.setNewValue(newValue);

			logService.save(orm);

			orm = new LogORM();
		} catch (Exception e) {
			log.severe("Error on method updateLog");
			e.printStackTrace();
		}
	}
}
