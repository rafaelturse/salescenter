package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;

@Converter
public class ForecastCategoryConverter implements AttributeConverter<ForecastCategoryEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ForecastCategoryEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public ForecastCategoryEnum convertToEntityAttribute(Integer value) {
		return ForecastCategoryEnum.valueOfId(value);
	}
}
