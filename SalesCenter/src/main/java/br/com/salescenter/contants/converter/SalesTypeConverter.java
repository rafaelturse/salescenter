package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.SalesTypeEnum;

@Converter
public class SalesTypeConverter implements AttributeConverter<SalesTypeEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(SalesTypeEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public SalesTypeEnum convertToEntityAttribute(Integer value) {
		return SalesTypeEnum.valueOfId(value);
	}
}