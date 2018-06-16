package br.ilegratest.converterdata;

import br.ilegratest.model.Data;

public abstract class ConverterData {
	protected final String SEPARATOR_CHARACTER = "ç";
	public abstract Data convert(String data);
}
