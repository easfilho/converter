package integration.summarysale;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.Item;
import br.ilegratest.model.SaleData;
import br.ilegratest.model.SaleSummary;
import br.ilegratest.model.SalesmanData;
import br.ilegratest.service.summarize.SaleSummarizer;
import br.ilegratest.service.summarize.Summarizer;

public class SumaryTest {

	@Test
	public void shoudSummarizeListOfData1() {
		List<SalesmanData> listSalesmanData = new ArrayList<>();
		List<CustomerData> listCustomerData = new ArrayList<>();
		List<SaleData> listSaleData = new ArrayList<>();
		List<Item> listItem = new ArrayList<>();

		listSalesmanData.add(new SalesmanData("123", "Joze", 100d));
		listCustomerData.add(new CustomerData("456", "Vitoria", "Rural"));
		listItem.add(new Item(1, 1, 5d));
		listSaleData.add(new SaleData(1, listItem, "Joze"));

		Summarizer summarizer = new SaleSummarizer();
		SaleSummary saleSummary = summarizer.summarize(listSalesmanData, listCustomerData, listSaleData);

		Assert.assertEquals(1, saleSummary.getAmountClients().intValue());
		Assert.assertEquals(1, saleSummary.getAmountSalesman().intValue());
		Assert.assertEquals(1, saleSummary.getIdMostExpensiveSale().intValue());
		Assert.assertEquals("Joze", saleSummary.getNameWorstSalesman());
	}

	@Test
	public void shoudSummarizeListOfData2() {
		List<SalesmanData> listSalesmanData = new ArrayList<>();
		List<CustomerData> listCustomerData = new ArrayList<>();
		List<SaleData> listSaleData = new ArrayList<>();

		listSalesmanData.add(new SalesmanData("111", "Joze", 100d));
		listSalesmanData.add(new SalesmanData("222", "Alfredo", 150d));
		listSalesmanData.add(new SalesmanData("333", "Maria", 300d));

		listCustomerData.add(new CustomerData("444", "Vitoria", "Rural"));
		listCustomerData.add(new CustomerData("555", "Ivone", "Rural"));
		listCustomerData.add(new CustomerData("777", "Pedro", "Comercio"));
		listCustomerData.add(new CustomerData("888", "Almir", "Rural"));

		List<Item> listItem = new ArrayList<>();
		listItem.add(new Item(1, 1, 5d));
		listSaleData.add(new SaleData(10, listItem, "Joze"));

		listItem = new ArrayList<>();
		listItem.add(new Item(1, 5, 5d));
		listItem.add(new Item(2, 10, 1d));
		
		listSaleData.add(new SaleData(11, listItem, "Alfredo"));
		
		listItem = new ArrayList<>();
		listItem.add(new Item(3, 1, 1d));
		listSaleData.add(new SaleData(12, listItem, "Maria"));

		Summarizer summarizer = new SaleSummarizer();
		SaleSummary saleSummary = summarizer.summarize(listSalesmanData, listCustomerData, listSaleData);

		Assert.assertEquals(4, saleSummary.getAmountClients().intValue());
		Assert.assertEquals(3, saleSummary.getAmountSalesman().intValue());
		Assert.assertEquals(11, saleSummary.getIdMostExpensiveSale().intValue());
		Assert.assertEquals("Maria", saleSummary.getNameWorstSalesman());
	}
}
