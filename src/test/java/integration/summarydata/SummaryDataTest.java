package integration.summarydata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ilegratest.service.file.DatFileManager;
import br.ilegratest.service.file.FileManager;
import br.ilegratest.service.summarize.SummaryData;
import br.ilegratest.service.summarize.SummaryDataImpl;

public class SummaryDataTest {

	@Test
	public void shoudSummarizeData() {
		List<String> lines = new ArrayList<>();

		lines.add("001�1234567891234�Diego�50000");
		lines.add("001�3245678865434�Renato�40000.99");
		lines.add("002�2345675434544345�Jose da Silva�Rural");
		lines.add("002�2345675433444345�Eduardo Pereira�Rural");
		lines.add("003�10�[1-10-100,2-30-2.50,3-40-3.10]�Diego");
		lines.add("003�08�[1-34-10,2-33-1.50,3-40-0.10]�Renato");

		String inPath = System.getProperty("user.dir") + "\\data\\in\\data.dat";
		prepareData(lines, inPath);

		SummaryData summaryData = new SummaryDataImpl();
		summaryData.execute();

		String outPath = System.getProperty("user.dir") + "\\data\\out\\data.done.dat";
		FileManager fileManagerOut = new DatFileManager(outPath);
		List<String> resultLines = fileManagerOut.read();
		Assert.assertEquals("Amount of clients: 2", resultLines.get(0));
		Assert.assertEquals("Amount of salesman: 2", resultLines.get(1));
		Assert.assertEquals("ID of the most expensive sale: 10", resultLines.get(2));
		Assert.assertEquals("Worst salesman ever: Renato", resultLines.get(3));
	}
	
	@Test
	public void shoudSummarizeDataTwoFiles() {
		SummaryData summaryData = new SummaryDataImpl();

		List<String> linesFirstFile = new ArrayList<>();
		linesFirstFile.add("001�1234567891234�Diego�50000");
		linesFirstFile.add("001�3245678865434�Renato�40000.99");
		linesFirstFile.add("002�2345675434544345�Jose da Silva�Rural");
		linesFirstFile.add("002�2345675433444345�Eduardo Pereira�Rural");
		linesFirstFile.add("003�10�[1-10-100,2-30-2.50,3-40-3.10]�Diego");
		linesFirstFile.add("003�08�[1-34-10,2-33-1.50,3-40-0.10]�Renato");

		String inPathFirstFile = System.getProperty("user.dir") + "\\data\\in\\FirstData.dat";
		prepareData(linesFirstFile, inPathFirstFile);
		
		List<String> linesSecondtFile = new ArrayList<>();
		linesSecondtFile.add("001�1234567891234�Diego�50000");
		linesSecondtFile.add("001�3245678865434�Renato�40000.99");
		linesSecondtFile.add("001�3245678865434�Vitoria�99999.99");
		linesSecondtFile.add("002�2345675434544345�Joao�Rural");
		linesSecondtFile.add("002�2345675433444345�Henrique�Rural");
		linesSecondtFile.add("003�99�[1-100-100]�Vitoria");
		linesSecondtFile.add("003�08�[1-1-1,2-1-1]�Renato");
		linesSecondtFile.add("003�08�[1-1-1]�Diego");

		String inPathSecondFile = System.getProperty("user.dir") + "\\data\\in\\secondData.dat";
		prepareData(linesSecondtFile, inPathSecondFile);

		summaryData.execute();
		
		String outPathFisrtFile = System.getProperty("user.dir") + "\\data\\out\\FirstData.done.dat";
		FileManager fileManagerFirstOut = new DatFileManager(outPathFisrtFile);
		List<String> resultFirstLines = fileManagerFirstOut.read();
		Assert.assertEquals("Amount of clients: 2", resultFirstLines.get(0));
		Assert.assertEquals("Amount of salesman: 2", resultFirstLines.get(1));
		Assert.assertEquals("ID of the most expensive sale: 10", resultFirstLines.get(2));
		Assert.assertEquals("Worst salesman ever: Renato", resultFirstLines.get(3));

		String outPathSecondtFile = System.getProperty("user.dir") + "\\data\\out\\secondData.done.dat";
		FileManager fileManagerSecondOut = new DatFileManager(outPathSecondtFile);
		List<String> resulSecondtLines = fileManagerSecondOut.read();
		Assert.assertEquals("Amount of clients: 2", resulSecondtLines.get(0));
		Assert.assertEquals("Amount of salesman: 3", resulSecondtLines.get(1));
		Assert.assertEquals("ID of the most expensive sale: 99", resulSecondtLines.get(2));
		Assert.assertEquals("Worst salesman ever: Diego", resulSecondtLines.get(3));
	}
	
	private void prepareData(List<String> lines, String inPath) {
		FileManager fileManagerIn = new DatFileManager(inPath);
		fileManagerIn.write(lines);
	}
}
