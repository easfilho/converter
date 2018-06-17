package integration.conversor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ilegratest.model.CustomerData;
import br.ilegratest.model.Item;
import br.ilegratest.model.SaleData;
import br.ilegratest.model.SalesmanData;
import br.ilegratest.service.converter.Converter;
import br.ilegratest.service.converter.ConverterFlatFile;

public class ConverterTest {

	@Test
	public void shouldConvertSalesmanData() {
		Converter converter = new ConverterFlatFile();
		SalesmanData salesmanData = (SalesmanData) converter.convert("001Á1234567891234ÁDiegoÁ50000");
		Assert.assertEquals("1234567891234", salesmanData.getCpf());
		Assert.assertEquals("Diego", salesmanData.getName());
		Assert.assertEquals(new Double("50000"), salesmanData.getSalary());
	}

	@Test
	public void shouldConvertCustormeData() {
		Converter converter = new ConverterFlatFile();
		CustomerData customerData = (CustomerData) converter.convert("002Á2345675434544345ÁJose da SilvaÁRural");
		Assert.assertEquals("2345675434544345", customerData.getCnpj());
		Assert.assertEquals("Jose da Silva", customerData.getName());
		Assert.assertEquals("Rural", customerData.getBusinessArea());
	}

	@Test
	public void shouldConvertSaleData() throws Exception {
		Converter converter = new ConverterFlatFile();
		SaleData saleData = (SaleData) converter.convert("003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁDiego");
		Assert.assertEquals(10, saleData.getId().intValue());
		Assert.assertEquals("Diego", saleData.getNameSalesman());
		List<Item> listItem = new ArrayList<>();
		listItem.add(new Item(1, 10, 100d));
		listItem.add(new Item(2, 30, 2.50d));
		listItem.add(new Item(3, 40, 3.10d));
		Assert.assertEquals(listItem.size(), saleData.getListItem().size());
		for (Item item : listItem) {
			Assert.assertTrue(saleData.getListItem().stream().anyMatch(x -> 
				x.getId().equals(item.getId()) && 
				x.getQuantity().equals(item.getQuantity()) && 
				x.getPrice().equals(item.getPrice())));
		}
	}
}
