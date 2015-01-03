package jms.model;

import java.io.Serializable;

public class ARequest implements Serializable {
	/**
	 * SerialVersionUID für ARequest
	 */
	private static final long serialVersionUID = 1337L;

	// ID der Consumer-Anfrage
	private String requestId;

	// Leih-Betrag
	private Double amount;

	// Zeitraum [in Monaten]
	private Integer term;

	// Bewertung des Consumers ()
	private Character consumerRate;

	// Deadline als Timestamp, kann auch null sein
	private Long requestDeadline;

	/* GETTER / SETTER */
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Character getConsumerRate() {
		return consumerRate;
	}

	public void setConsumerRate(Character consumerRate) {
		this.consumerRate = consumerRate;
	}

	public Long getRequestDeadline() {
		return requestDeadline;
	}

	public void setRequestDeadline(Long requestDeadline) {
		this.requestDeadline = requestDeadline;
	}
}
