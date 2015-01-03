package de.hs.furtwangen.bam.bi.jmsBank.exception;

public class JMSServiceException extends Exception {
	private static final long serialVersionUID = 7025914922723408356L;

	private Throwable payload;

	public JMSServiceException(Throwable payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return payload.toString();
	}

}