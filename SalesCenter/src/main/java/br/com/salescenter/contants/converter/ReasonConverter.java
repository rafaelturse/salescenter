package br.com.salescenter.contants.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.salescenter.contants.enumeration.ReasonEnum;

@Converter
public class ReasonConverter implements AttributeConverter<ReasonEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ReasonEnum value) {
		return value != null ? value.getId() : null;
	}

	@Override
	public ReasonEnum convertToEntityAttribute(Integer value) {
		return ReasonEnum.valueOfId(value);
	}
}
