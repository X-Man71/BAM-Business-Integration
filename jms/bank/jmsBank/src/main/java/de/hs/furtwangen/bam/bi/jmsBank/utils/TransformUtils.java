package de.hs.furtwangen.bam.bi.jmsBank.utils;

import java.util.Properties;

import javax.annotation.Resource;

import jms.model.ARequest;
import jms.model.AResponse;

import org.springframework.stereotype.Component;

import de.hs.furtwangen.bam.bi.jmsBank.model.Request;

@Component
public class TransformUtils {
	
	@Resource(name = "configProperties")
	private Properties configProperties;

	public Request transformJMS2Model(ARequest request) {
		Request result = new Request();
		result.requestId = request.getRequestId();
		result.amount = request.getAmount();
		result.term = request.getTerm();
		result.consumerRate = request.getConsumerRate();
		result.requestDeadline = request.getRequestDeadline();
		return result;
	}

	public AResponse transformRequest2Response(Request request) {
		AResponse response = new AResponse();
		response.setBankName(configProperties.getProperty("bank.name",
				"??jmsBank??"));
		response.setQuoteRate(request.quoteRate);
		response.setRequestId(request.requestId);
		return response;
	}
}
