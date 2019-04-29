package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.StateEnum;

@Converter
public class StateConverter implements AttributeConverter<StateEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(StateEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public StateEnum convertToEntityAttribute(Integer value) {
		return StateEnum.valueOfId(value);
	}
}
