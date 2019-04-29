package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;

@Converter
public class SalesStageConverter implements AttributeConverter<SalesStageEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(SalesStageEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public SalesStageEnum convertToEntityAttribute(Integer value) {
		return SalesStageEnum.valueOfId(value);
	}
}
