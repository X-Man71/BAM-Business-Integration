package jms.model;

import java.io.Serializable;

public class AResponse implements Serializable {
	/**
	 * SerialVersionUID für AResponse
	 */
	private static final long serialVersionUID = 42L;

	// Name der Bank
	private String bankName;

	// ID der Consumer-Anfrage
	private String requestId;

	// Zinssatz
	private Double quoteRate;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Double getQuoteRate() {
		return quoteRate;
	}

	public void setQuoteRate(Double quoteRate) {
		this.quoteRate = quoteRate;
	}

}
