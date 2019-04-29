package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.GenderEnum;

@Converter
public class GenderConverter implements AttributeConverter<GenderEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(GenderEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public GenderEnum convertToEntityAttribute(Integer value) {
		return GenderEnum.valueOfId(value);
	}
}
