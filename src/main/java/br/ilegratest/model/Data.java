package br.ilegratest.model;

public abstract class Data {
	public boolean isClass(Class<?> clazz) {
		return this.getClass().equals(clazz);
	}
}
