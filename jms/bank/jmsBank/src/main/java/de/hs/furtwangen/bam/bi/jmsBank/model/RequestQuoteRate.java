package de.hs.furtwangen.bam.bi.jmsBank.model;

import javax.persistence.Id;

public class RequestQuoteRate {
	
	@Id
	private String requestId;

	private String quoteRate;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getQuoteRate() {
		return quoteRate;
	}

	public void setQuoteRate(String quoteRate) {
		this.quoteRate = quoteRate;
	}
	
	

}
