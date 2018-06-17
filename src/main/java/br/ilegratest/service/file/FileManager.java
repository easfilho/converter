package br.ilegratest.service.file;

import java.io.IOException;
import java.util.List;

public abstract class FileManager {
	protected String path;
	public abstract void write(List<String> lines) throws IOException;
	public abstract List<String> read() throws IOException;
}
