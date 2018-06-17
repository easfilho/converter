package br.ilegratest.model;

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
}
