package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.SegmentEnum;

@Converter
public class SegmentConverter implements AttributeConverter<SegmentEnum, Integer> {
	
	@Override
	public Integer convertToDatabaseColumn(SegmentEnum value) {
		return value != null ? value.getId() : null; 
	}

	@Override
	public SegmentEnum convertToEntityAttribute(Integer value) {
		return SegmentEnum.valueOfId(value);
	}	
}