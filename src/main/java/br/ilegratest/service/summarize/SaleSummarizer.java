package br.ilegratest.service.summarize;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.SaleData;
import br.ilegratest.model.SaleSummary;
import br.ilegratest.model.SalesmanData;

public class SaleSummarizer implements Summarizer {

	@Override
	public SaleSummary summarize(List<SalesmanData> listSalesmanData, List<CustomerData> listCustomerData,
			List<SaleData> listSaleData) {
		Integer amountClients = getAmountCustomers(listCustomerData);
		Integer amountSalesman = getAmountSalesman(listSalesmanData);
		Integer idMostExpensiveSale = getIdMostExpensiveSave(listSaleData);
		String worstSalesman = getWorstSalesman(listSalesmanData, listSaleData);
		return new SaleSummary(amountClients, amountSalesman, idMostExpensiveSale, worstSalesman);
	}

	private int getAmountSalesman(List<SalesmanData> listSalesmanData) {
		return listSalesmanData.size();
	}

	private int getAmountCustomers(List<CustomerData> listCustomerData) {
		return listCustomerData.size();
	}

	private Integer getIdMostExpensiveSave(List<SaleData> listSaleData) {
		return Collections.max(listSaleData, Comparator.comparing(SaleData::getValue)).getId();
	}

	private String getWorstSalesman(List<SalesmanData> listSalesmanData, List<SaleData> listSaleData) {
		Map<String, Double> mapSalesSalesman = new HashMap<>();
		for (SalesmanData salesman : listSalesmanData) {
			List<SaleData> listSaleSalesman = listSaleData.stream().filter(x -> x.hasSalesman(salesman.getName()))
					.collect(Collectors.toList());
			Double sumSale = getSumOfSales(listSaleSalesman);
			mapSalesSalesman.put(salesman.getName(), sumSale);
		}

		return getSalesmanOfMinimunSale(mapSalesSalesman);
	}

	private String getSalesmanOfMinimunSale(Map<String, Double> mapSalesSalesman) {
		Entry<String, Double> min = Collections.min(mapSalesSalesman.entrySet(), Comparator.comparing(Entry::getValue));
		return min.getKey();
	}

	private Double getSumOfSales(List<SaleData> listSaleData) {
		return listSaleData.stream().mapToDouble(x -> x.getValue()).sum();
	}

}
