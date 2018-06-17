package br.ilegratest.enums;

import java.util.Arrays;
import java.util.List;

import br.ilegratest.service.converterdata.ConverterCustomerData;
import br.ilegratest.service.converterdata.ConverterData;
import br.ilegratest.service.converterdata.ConverterSaleData;
import br.ilegratest.service.converterdata.ConverterSalesmanData;

public enum TypeDataEnum {
	SALESMAN	("001", new ConverterSalesmanData()),
	CUSTOMER	("002", new ConverterCustomerData()),
	SALE		("003", new ConverterSaleData());

	private String prefix;
	private ConverterData converterData;

	private TypeDataEnum(String prefix, ConverterData converterData) {
		this.prefix = prefix;
		this.converterData = converterData;
	}

	public ConverterData getConverterData() {
		return converterData;
	}

	public static TypeDataEnum getFromPrefix(String prefix) {
		List<TypeDataEnum> listTypeDataEnum = Arrays.asList(values());
		return listTypeDataEnum.stream().filter(x -> x.prefix.equals(prefix)).findFirst().orElse(null);
	}
}
