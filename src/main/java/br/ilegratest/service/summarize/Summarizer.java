package br.ilegratest.service.summarize;

import java.util.List;

import br.ilegratest.model.Data;
import br.ilegratest.model.SaleSummary;

public interface Summarizer {
	public SaleSummary summarize(List<Data> listData);
}
