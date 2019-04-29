package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.ActionEnum;

@Converter
public class ActionConverter implements AttributeConverter<ActionEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ActionEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public ActionEnum convertToEntityAttribute(Integer value) {
		return ActionEnum.valueOfId(value);
	}
}