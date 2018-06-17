package br.ilegratest.service.converter;

import br.ilegratest.enums.TypeDataEnum;
import br.ilegratest.model.Data;

public class ConverterFlatFile implements Converter {

	private static final int BEGIN_PREFIX = 0;
	private static final int END_PREFIX = 3;

	@Override
	public Data convert(String data) {
		String prefix = data.substring(BEGIN_PREFIX, END_PREFIX);
		TypeDataEnum typeDataEnum = TypeDataEnum.getFromPrefix(prefix);
		return typeDataEnum.getConverterData().convert(data);
	}
}
