package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;

@Converter
public class BusinessTypeConverter implements AttributeConverter<BusinessTypeEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(BusinessTypeEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public BusinessTypeEnum convertToEntityAttribute(Integer value) {
		return BusinessTypeEnum.valueOfId(value);
	}
}
