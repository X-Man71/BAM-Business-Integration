package com.test.client;

import com.test.IVBank;
import com.test.VBankService;

public class ClientForWebservice {

	public static void main(String[] args) {
		IVBank ivBank = new VBankService().getVBankPort();

		Double amount = 2000.00;
		Integer term = 12;
		String consumerRate = "a";
		Double quoteRate = ivBank.getCreditRate(amount, term, consumerRate);
		System.out.println("quoteRate "+quoteRate);
	}

}

