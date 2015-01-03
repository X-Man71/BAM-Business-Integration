package de.hs.furtwangen.bam.bi.jmsBank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Request {

	public Request() {
		created = new Date();
		validMsg = true;
	}

	// ID der Consumer-Anfrage
	@Id
	public String requestId;

	// Leih-Betrag
	public Double amount;

	// Zeitraum [in Monaten]
	public Integer term;

	// Bewertung des Consumers ()
	public Character consumerRate;

	// Deadline als Timestamp, kann auch null sein
	public Long requestDeadline;

	@DateTimeFormat(pattern = "HH.mm.ss dd.MM.yyyy")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date created;

	public Double quoteRate;

	public Boolean validMsg;
}
