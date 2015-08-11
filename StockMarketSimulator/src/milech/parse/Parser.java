package milech.parse;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import milech.entity.Stock;

public class Parser {
	public static Date parseDate(String date) {
		Date resultDate = null;
		DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
		try {
			resultDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}


	public static float parsePrice(String price) {
		float resultPrice = 0;
		try {
			DecimalFormat format = new DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			resultPrice = format.parse(price).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultPrice;
	}
	
	public static int convMoneyToStock(float money, Stock stock) {
		return (int) (money / stock.getPrice());
	}
}
