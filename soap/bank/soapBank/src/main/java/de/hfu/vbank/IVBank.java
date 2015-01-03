package de.hfu.vbank;

import javax.jws.WebService;

@WebService(name = "IVBank", targetNamespace = "http://vbank.hfu.de/")
public interface IVBank {

	public Double getCreditRate(double amount, int termInMonth, String rating);

}