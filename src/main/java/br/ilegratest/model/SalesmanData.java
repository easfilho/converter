package br.ilegratest.model;

public class SalesmanData extends Data {
	private String cpf;
	private String name;
	private Double salary;

	public SalesmanData(String cpf, String name, Double salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public Double getSalary() {
		return salary;
	}	
}
