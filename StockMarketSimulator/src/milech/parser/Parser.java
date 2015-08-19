package milech.parser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	
	public static int determineAmountOfStockToBuy(float money, float stockPrice) {
		return (int) (money / stockPrice);
	}
	
	public static float round(float money, int pointPosition) {
		double position = (int)Math.pow(10, pointPosition);
		return (float)(Math.round(money*position)/position);
	}
}
