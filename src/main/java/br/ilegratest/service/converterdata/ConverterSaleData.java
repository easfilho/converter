package br.ilegratest.service.converterdata;

import java.util.ArrayList;
import java.util.List;

import br.ilegratest.model.Data;
import br.ilegratest.model.Item;
import br.ilegratest.model.SaleData;

public class ConverterSaleData extends ConverterData {

	private static final int POSITION_ID_SALE = 1;
	private static final int POSITION_LIST_ITEM = 2;
	private static final int POSITION_NAME_SALESMAN = 3;

	private static final int POSITION_ID_ITEM = 0;
	private static final int POSITION_QUANTITY_ITEM = 1;
	private static final int POSITION_PRICE_ITEM = 2;

	@Override
	public Data convert(String data) {
		String[] fields = data.split(super.SEPARATOR_CHARACTER);
		Integer id = new Integer(fields[POSITION_ID_SALE]);
		List<Item> listItem = convertListItem(fields[POSITION_LIST_ITEM]);
		String nameSalesman = fields[POSITION_NAME_SALESMAN];
		return new SaleData(id, listItem, nameSalesman);
	}

	private List<Item> convertListItem(String dataListItem) {
		List<Item> listItem = new ArrayList<>();
		String[] arrayItem = dataListItem.substring(1, dataListItem.length() - 1).split(",");
		for (String dataItem : arrayItem) {
			Item item = converItem(dataItem);
			listItem.add(item);
		}
		return listItem;
	}

	private Item converItem(String dataItem) {
		String[] fieldsItem = dataItem.split("-");
		Integer id = new Integer(fieldsItem[POSITION_ID_ITEM]);
		Integer quantity = new Integer(fieldsItem[POSITION_QUANTITY_ITEM]);
		Double price = new Double(fieldsItem[POSITION_PRICE_ITEM]);
		Item item = new Item(id, quantity, price);
		return item;
	}

}
