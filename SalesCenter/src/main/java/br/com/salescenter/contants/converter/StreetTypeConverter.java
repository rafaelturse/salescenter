package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.StreetTypeEnum;

@Converter
public class StreetTypeConverter implements AttributeConverter<StreetTypeEnum, Integer> {
	
	@Override
	public Integer convertToDatabaseColumn(StreetTypeEnum value) {
		return value != null ? value.getId() : null; 
	}

	@Override
	public StreetTypeEnum convertToEntityAttribute(Integer value) {
		return StreetTypeEnum.valueOfId(value);
	}	
}