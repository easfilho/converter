package br.ilegratest.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatFileManager extends FileManager {

	public DatFileManager(String path) {
		super();
		super.path = path;
	}

	@Override
	public void write(List<String> lines) throws IOException {
		File file = new File(super.path);
		file.getParentFile().mkdirs();
		try (OutputStream outputStream = new FileOutputStream(file)) {
			file.createNewFile();
			for (String line : lines) {
				outputStream.write(line.getBytes());
				outputStream.write("\n".getBytes());
			}
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public List<String> read() throws IOException {
		List<String> lines = new ArrayList<>();
		try (InputStream is = new FileInputStream(path)) {
			try (Scanner scaneer = new Scanner(is)) {
				while (scaneer.hasNext()) {
					lines.add(scaneer.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
		return lines;
	}
}
