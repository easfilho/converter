package br.ilegratest.service.converterdata;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.Data;

public class ConverterCustomerData extends ConverterData {

	private static final int POSITION_CNPJ = 1;
	private static final int POSITION_NAME = 2;
	private static final int POSTION_BUSINESS_AREA = 3;

	@Override
	public Data convert(String data) {
		String[] fields = data.split(SEPARATOR_CHARACTER);
		String cnpj = fields[POSITION_CNPJ];
		String name = fields[POSITION_NAME];
		String businessArea = fields[POSTION_BUSINESS_AREA];
		return new CustomerData(cnpj, name, businessArea);
	}

}
