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
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			file.createNewFile();
			for (String line : lines) {
				outputStream.write(line.getBytes());
				outputStream.write("\n".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}

	}

	@Override
	public List<String> read() throws IOException {
		List<String> lines = new ArrayList<>();
		InputStream is = null;
		Scanner scan = null;
		try {
			is = new FileInputStream(path);
			scan = new Scanner(is);
			while (scan.hasNext()) {
				lines.add(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
			if (scan != null) {
				scan.close();
			}
		}

		return lines;
	}
}
