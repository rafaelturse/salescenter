package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.PhoneTypeEnum;

@Converter
public class PhoneTypeConverter implements AttributeConverter<PhoneTypeEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(PhoneTypeEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public PhoneTypeEnum convertToEntityAttribute(Integer value) {
		return PhoneTypeEnum.valueOfId(value);
	}
}
