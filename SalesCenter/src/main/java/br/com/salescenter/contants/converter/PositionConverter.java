package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.PositionEnum;

@Converter
public class PositionConverter implements AttributeConverter<PositionEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(PositionEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public PositionEnum convertToEntityAttribute(Integer value) {
		return PositionEnum.valueOfId(value);
	}
}
