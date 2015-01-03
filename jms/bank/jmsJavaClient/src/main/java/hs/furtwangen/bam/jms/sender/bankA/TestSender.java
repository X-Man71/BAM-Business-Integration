package hs.furtwangen.bam.jms.sender.bankA;


import java.util.UUID;

import javax.jms.JMSException;
import javax.naming.NamingException;

import jms.model.ARequest;

public class TestSender {

	public static void main(String[] args) throws JMSException, NamingException {
		
		ARequest aRequest = new ARequest();
		aRequest.setAmount(900.00);
		//char consumerRate = 'a';
		aRequest.setConsumerRate('B');
		aRequest.setRequestDeadline(null);
		aRequest.setRequestId(UUID.randomUUID().toString());
		aRequest.setTerm(6);
		
		MsgSender.getInstance().sendObjectMessage(aRequest);
		System.out.println("Message send!");
		System.exit(0);
	}
}
