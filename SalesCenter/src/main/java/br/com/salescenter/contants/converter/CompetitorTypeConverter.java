package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.CompetitorTypeEnum;

@Converter
public class CompetitorTypeConverter implements AttributeConverter<CompetitorTypeEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(CompetitorTypeEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public CompetitorTypeEnum convertToEntityAttribute(Integer value) {
		return CompetitorTypeEnum.valueOfId(value);
	}
}
