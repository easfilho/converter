package br.ilegratest.service.converterdata;

import br.ilegratest.model.Data;

public abstract class ConverterData {
	protected static final String SEPARATOR_CHARACTER = "�";
	public abstract Data convert(String data);
}
