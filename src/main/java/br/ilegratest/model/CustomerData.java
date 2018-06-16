package br.ilegratest.model;

public class CustomerData extends Data {
	private String cnpj;
	private String name;
	private String businessArea;

	public CustomerData(String cnpj, String name, String businessArea) {
		super();
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getName() {
		return name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

}
