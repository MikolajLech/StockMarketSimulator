package milech.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import milech.entity.Stock;

public class StockMarketImpl implements StockMarket {

	private List<Stock> stocks = new ArrayList<Stock>();
	private File dataSource;
	
	@Override
	public void loadNextStock() {

		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		Stock newStock = new Stock();
		try {
			br = new BufferedReader(new FileReader(dataSource));
//			while ((line = br.readLine()) != null) {
			if((line = br.readLine()) != null) {
				// use comma as separator
				String[] stockData = line.split(csvSplitBy);
				newStock.setName(stockData[0]);
				newStock.setDate(stockData[1]);
				newStock.setPrice(stockData[2]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		stocks.add(newStock);
	}
	
	@Override
	public void setDataSource(String dataSource) {
		this.dataSource = new File(dataSource);
	}
	
	@Override
	public Stock getStock(int index) {
		return stocks.get(index); 
	}

	
}
