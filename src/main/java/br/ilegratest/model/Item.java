package br.ilegratest.model;

public class Item {
	private Integer id;
	private Integer quantity;
	private Double price;

	public Item(Integer id, Integer quantity, Double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}

}
