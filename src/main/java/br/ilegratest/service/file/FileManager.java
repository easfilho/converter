package br.ilegratest.service.file;

import java.util.List;

public abstract class FileManager {
	protected String path;
	public abstract void write(List<String> lines);
	public abstract List<String> read();
}
