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
	public void write(List<String> lines) {
		try {
			File file = new File(super.path);
			file.getParentFile().mkdirs();
			file.createNewFile();
			OutputStream outputStream = new FileOutputStream(file);
			for (String line : lines) {
				outputStream.write(line.getBytes());
				outputStream.write("\n".getBytes());
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> read() {
		List<String> lines = new ArrayList<>();
		try (InputStream is = new FileInputStream(path)) {
			Scanner scan = new Scanner(is);
			while (scan.hasNext()) {
				lines.add(scan.nextLine());
			}
			scan.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lines;
	}
}
