package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.ScreenEnum;

@Converter
public class ScreenConverter implements AttributeConverter<ScreenEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(ScreenEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public ScreenEnum convertToEntityAttribute(Integer value) {
		return ScreenEnum.valueOfId(value);
	}
}
