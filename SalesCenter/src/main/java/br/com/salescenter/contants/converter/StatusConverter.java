package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.StatusEnum;

@Converter
public class StatusConverter implements AttributeConverter<StatusEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(StatusEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public StatusEnum convertToEntityAttribute(Integer value) {
		return StatusEnum.valueOfId(value);
	}
}
