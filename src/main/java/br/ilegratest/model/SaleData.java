package br.ilegratest.model;

import java.util.List;

public class SaleData extends Data {
	private Integer id;
	private List<Item> listItem;
	private String nameSalesman;

	public SaleData(Integer id, List<Item> listItem, String nameSalesman) {
		super();
		this.id = id;
		this.listItem = listItem;
		this.nameSalesman = nameSalesman;
	}

	public Integer getId() {
		return id;
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public String getNameSalesman() {
		return nameSalesman;
	}

}
