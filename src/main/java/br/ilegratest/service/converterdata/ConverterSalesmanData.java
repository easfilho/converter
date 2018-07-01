package br.ilegratest.service.converterdata;

import br.ilegratest.model.Data;
import br.ilegratest.model.SalesmanData;

public class ConverterSalesmanData extends ConverterData {

	@Override
	public Data convert(String data) {
		String[] fields = data.split(SEPARATOR_CHARACTER);
		String cpf = fields[1];
		String name = fields[2];
		Double salary = new Double(fields[3]);
		return new SalesmanData(cpf, name, salary);
	}
	
}
