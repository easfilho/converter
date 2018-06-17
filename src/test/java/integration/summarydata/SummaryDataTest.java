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

		lines.add("001Á1234567891234ÁDiegoÁ50000");
		lines.add("001Á3245678865434ÁRenatoÁ40000.99");
		lines.add("002Á2345675434544345ÁJose da SilvaÁRural");
		lines.add("002Á2345675433444345ÁEduardo PereiraÁRural");
		lines.add("003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁDiego");
		lines.add("003Á08Á[1-34-10,2-33-1.50,3-40-0.10]ÁRenato");

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
		linesFirstFile.add("001Á1234567891234ÁDiegoÁ50000");
		linesFirstFile.add("001Á3245678865434ÁRenatoÁ40000.99");
		linesFirstFile.add("002Á2345675434544345ÁJose da SilvaÁRural");
		linesFirstFile.add("002Á2345675433444345ÁEduardo PereiraÁRural");
		linesFirstFile.add("003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁDiego");
		linesFirstFile.add("003Á08Á[1-34-10,2-33-1.50,3-40-0.10]ÁRenato");

		String inPathFirstFile = System.getProperty("user.dir") + "\\data\\in\\FirstData.dat";
		prepareData(linesFirstFile, inPathFirstFile);
		
		List<String> linesSecondtFile = new ArrayList<>();
		linesSecondtFile.add("001Á1234567891234ÁDiegoÁ50000");
		linesSecondtFile.add("001Á3245678865434ÁRenatoÁ40000.99");
		linesSecondtFile.add("001Á3245678865434ÁVitoriaÁ99999.99");
		linesSecondtFile.add("002Á2345675434544345ÁJoaoÁRural");
		linesSecondtFile.add("002Á2345675433444345ÁHenriqueÁRural");
		linesSecondtFile.add("003Á99Á[1-100-100]ÁVitoria");
		linesSecondtFile.add("003Á08Á[1-1-1,2-1-1]ÁRenato");
		linesSecondtFile.add("003Á08Á[1-1-1]ÁDiego");

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
