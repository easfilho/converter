package br.ilegratest.service.summarize;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.ilegratest.model.Data;
import br.ilegratest.model.SaleSummary;
import br.ilegratest.service.converter.Converter;
import br.ilegratest.service.converter.ConverterFlatFile;
import br.ilegratest.service.file.DatFileManager;
import br.ilegratest.service.file.FileManager;

public class SummaryDataImpl implements SummaryData {

	private static final String PATH_IN_DIRECTORY = "\\data\\in";
	private static final String PATH_OUT_DIRECTORY = "\\data\\out\\%s.done.dat";

	private Summarizer summarizer;
	private Converter converter;

	@Override
	public void execute() throws IOException {
		this.summarizer = new SaleSummarizer();
		List<Path> listPaths = getAllDatFilesFromDirectory();
		for (Path path : listPaths) {
			List<String> lines = readDatFile(path);
			List<Data> listData = convertLines(lines);
			writeSummary(listData, path);
		}

	}

	private void writeSummary(List<Data> listData, Path path) throws IOException {
		String fileName = getFlatFileName(path);
		SaleSummary saleSummary = this.summarizer.summarize(listData);
		FileManager fileManager = new DatFileManager(
				System.getProperty("user.dir") + String.format(PATH_OUT_DIRECTORY, fileName));
		fileManager.write(saleSummary.getSummaryInLines());
	}

	private String getFlatFileName(Path path) {
		String[] files = path.toString().split("\\\\");
		String nameFile = files[files.length - 1].split("\\.")[0];
		return nameFile;
	}

	private List<Data> convertLines(List<String> lines) {
		List<Data> listData = new ArrayList<>();
		this.converter = new ConverterFlatFile();
		for (String line : lines) {
			Data data = this.converter.convert(line);
			listData.add(data);
		}
		return listData;
	}

	private List<String> readDatFile(Path path) throws IOException {
		List<String> lines = new ArrayList<>();
		FileManager fileManager = new DatFileManager(path.toString());
		lines.addAll(fileManager.read());
		return lines;
	}

	private List<Path> getAllDatFilesFromDirectory() throws IOException {
		return Files.walk(Paths.get(System.getProperty("user.dir") + PATH_IN_DIRECTORY)).filter(Files::isRegularFile)
				.filter(x -> x.toString().endsWith(".dat")).collect(Collectors.toList());
	}
}
