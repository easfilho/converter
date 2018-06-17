package br.ilegratest.model;

public abstract class Data {
	protected Integer id;

	public boolean isClass(Class<?> clazz) {
		return this.getClass().equals(clazz);
	}
}
