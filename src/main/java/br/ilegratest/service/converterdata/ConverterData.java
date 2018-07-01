package br.ilegratest.service.converterdata;

import br.ilegratest.model.Data;

public abstract class ConverterData {
	protected static final String SEPARATOR_CHARACTER = "ç";
	public abstract Data convert(String data);
}
