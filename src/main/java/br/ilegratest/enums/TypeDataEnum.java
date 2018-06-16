package br.ilegratest.enums;

import java.util.Arrays;
import java.util.List;

import br.ilegratest.converterdata.ConverterCustomerData;
import br.ilegratest.converterdata.ConverterData;
import br.ilegratest.converterdata.ConverterSaleData;
import br.ilegratest.converterdata.ConverterSalesmanData;

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

	public String getPrefix() {
		return prefix;
	}

	public ConverterData getConverterData() {
		return converterData;
	}

	public static TypeDataEnum getFromPrefix(String prefix) {
		List<TypeDataEnum> listTypeDataEnum = Arrays.asList(values());
		return listTypeDataEnum.stream().filter(x -> x.prefix.equals(prefix)).findFirst().orElse(null);
	}
}
