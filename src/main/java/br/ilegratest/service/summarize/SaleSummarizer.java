package br.ilegratest.service.summarize;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.Data;
import br.ilegratest.model.SaleData;
import br.ilegratest.model.SaleSummary;
import br.ilegratest.model.SalesmanData;

public class SaleSummarizer implements Summarizer {

	@Override
	public SaleSummary summarize(List<Data> listData) {
		Integer amountClients = getAmountCustomers(listData);
		Integer amountSalesman = getAmountSalesman(listData);
		Integer idMostExpensiveSale = getIdMostExpensiveSale(listData);
		String worstSalesman = getWorstSalesman(listData);
		return new SaleSummary(amountClients, amountSalesman, idMostExpensiveSale, worstSalesman);
	}

	private int getAmountSalesman(List<Data> listData) {
		return listData.stream().filter(x -> x.isClass(SalesmanData.class)).collect(Collectors.toList()).size();
	}

	private int getAmountCustomers(List<Data> listData) {
		return listData.stream().filter(x -> x.isClass(CustomerData.class)).collect(Collectors.toList()).size();
	}

	private Integer getIdMostExpensiveSale(List<Data> listData) {
		List<SaleData> listSaleData = getListAsSaleDataList(listData);
		return Collections.max(listSaleData, Comparator.comparing(SaleData::getValue)).getId();
	}

	private String getWorstSalesman(List<Data> listData) {
		List<SaleData> listSaleData = getListAsSaleDataList(listData);
		List<SalesmanData> listSalesmanData = getListAsSalesmanData(listData);
		
		Map<String, Double> mapSalesSalesman = new HashMap<>();
		for (SalesmanData salesman : listSalesmanData) {
			List<SaleData> listSaleSalesman = listSaleData.stream().filter(x -> x.hasSalesman(salesman.getName()))
					.collect(Collectors.toList());
			Double sumSale = getSumOfSales(listSaleSalesman);
			mapSalesSalesman.put(salesman.getName(), sumSale);
		}

		return getSalesmanOfMinimunSale(mapSalesSalesman);
	}

	private List<SalesmanData> getListAsSalesmanData(List<Data> listData) {
		return listData.stream().filter(x -> x.isClass(SalesmanData.class)).map(x -> (SalesmanData) x)
				.collect(Collectors.toList());
	}

	private List<SaleData> getListAsSaleDataList(List<Data> listData) {
		return listData.stream().filter(x -> x.isClass(SaleData.class)).map(x -> (SaleData) x)
				.collect(Collectors.toList());
	}

	private String getSalesmanOfMinimunSale(Map<String, Double> mapSalesSalesman) {
		Entry<String, Double> min = Collections.min(mapSalesSalesman.entrySet(), Comparator.comparing(Entry::getValue));
		return min.getKey();
	}

	private Double getSumOfSales(List<SaleData> listSaleData) {
		return listSaleData.stream().mapToDouble(x -> x.getValue()).sum();
	}

}
