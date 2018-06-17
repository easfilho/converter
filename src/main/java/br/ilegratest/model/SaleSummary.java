package br.ilegratest.model;

import java.util.ArrayList;
import java.util.List;

public class SaleSummary {
	private Integer amountClients;
	private Integer amountSalesman;
	private Integer idMostExpensiveSale;
	private String nameWorstSalesman;

	public SaleSummary(Integer amountClients, Integer amountSalesman, Integer idMostExpensiveSale,
			String nameWorsSalesman) {
		super();
		this.amountClients = amountClients;
		this.amountSalesman = amountSalesman;
		this.idMostExpensiveSale = idMostExpensiveSale;
		this.nameWorstSalesman = nameWorsSalesman;
	}

	public Integer getAmountClients() {
		return amountClients;
	}

	public Integer getAmountSalesman() {
		return amountSalesman;
	}

	public Integer getIdMostExpensiveSale() {
		return idMostExpensiveSale;
	}

	public String getNameWorstSalesman() {
		return nameWorstSalesman;
	}

	public List<String> getSummaryInLines() {
		List<String> lines = new ArrayList<>();
		lines.add("Amount of clients: " + this.amountClients);
		lines.add("Amount of salesman: " + this.amountSalesman);
		lines.add("ID of the most expensive sale: " + this.idMostExpensiveSale);
		lines.add("Worst salesman ever: " + this.nameWorstSalesman);
		return lines;
	}
}
