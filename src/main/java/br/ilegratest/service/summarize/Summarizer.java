package br.ilegratest.service.summarize;

import java.util.List;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.SaleData;
import br.ilegratest.model.SaleSummary;
import br.ilegratest.model.SalesmanData;

public interface Summarizer {
	public SaleSummary summarize(List<SalesmanData> listSalesmanData, List<CustomerData> listCustomerData,
			List<SaleData> listSaleData);
}
