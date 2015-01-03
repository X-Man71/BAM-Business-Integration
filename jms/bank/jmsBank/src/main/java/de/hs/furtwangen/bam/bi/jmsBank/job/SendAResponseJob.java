package de.hs.furtwangen.bam.bi.jmsBank.job;

import jms.model.AResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hs.furtwangen.bam.bi.jmsBank.jms.sender.MsgSender;
import de.hs.furtwangen.bam.bi.jmsBank.model.Request;
import de.hs.furtwangen.bam.bi.jmsBank.utils.TransformUtils;

@Component
public class SendAResponseJob {
	
	@Autowired
	private TransformUtils transformUtils;
	
	@Autowired
	private MsgSender msgSender;

	public void doJob(Request request) throws Exception {		
		
		AResponse response = transformUtils.transformRequest2Response(request);
		
		msgSender.registerNewMessageSender();
		
		msgSender.sendObjectMessage(response);
		System.out.println("Response sended!");
	}
}
