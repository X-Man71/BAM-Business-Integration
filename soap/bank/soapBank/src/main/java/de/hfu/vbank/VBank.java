package de.hfu.vbank;
import java.util.Random;

import javax.jws.WebService;

@WebService(targetNamespace = "http://vbank.hfu.de/", endpointInterface = "de.hfu.vbank.IVBank", portName = "VBankPort", serviceName = "VBankService")
public class VBank implements IVBank {

	public Double getCreditRate(double amount, int termInMonth, String rating) {
		// Is Rating invalid?
		if(!isValidRating(rating)) {
			return null;
		}
		
		// Is term over three years?
		if(termInMonth > 12 * 3) {
			return null;
		}
		
		return getRandomQuoteRate(termInMonth, amount, rating.charAt(0));
	}
	
private Double getRandomQuoteRate(Integer termInMonth, double amount, Character rating) {
		
		rating = Character.toLowerCase(rating);
		
		double value = (termInMonth % 12);
		value += (amount % 1000);
		value += (rating - 'a') % 10;
		value += new Random().nextDouble() * 3;
		return value % 10;
	}
	
	private boolean isValidRating(String rating) {
		if(rating == null || rating.length() != 1) {
			return false;
		}
		
		char character = rating.toLowerCase().charAt(0);
		System.out.println(Integer.valueOf(character));
		// 97 = a - 112 = p
		if(character < 97 || character > 112 ) {
			return false;
		}
		
		return true;
	}

}
