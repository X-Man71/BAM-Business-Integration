package de.hs.furtwangen.bam.bi.jmsBank.job;

import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;

import jms.model.ARequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hs.furtwangen.bam.bi.jmsBank.model.Request;
import de.hs.furtwangen.bam.bi.jmsBank.service.RequestService;
import de.hs.furtwangen.bam.bi.jmsBank.utils.TransformUtils;
import de.hs.furtwangen.bam.bi.jmsBank.utils.ValidatorUtils;
/**
 * {@link ARequest} messages are recieved with {@link #doJob(ARequest)} and saved in Database when Message is valide.
 * Messages are safed in Database that after a page refresh Message is available.
 * 
 * @author Christian Henle
 */
@Component
public class HandleARequestJob {

	
	@Resource(name = "configProperties")
	private Properties configProperties;
	
	@Autowired
	private ValidatorUtils validatorUtils;
	
	@Autowired
	private SendAResponseJob sendAResponseJob;
	
	@Autowired
	private RequestService requestService;


	public void doJob(ARequest request) throws Exception {
		Request reqDB = new TransformUtils().transformJMS2Model(request);

		// Check if Request is valid
		if (!isValidRequest(request)) {
			// Create invalid request
			reqDB.validMsg = false;
		}
		
		requestService.save(reqDB);
	
		// Automode and is valid Message?
		if (isAutomode() && reqDB.validMsg) {
			reqDB.quoteRate = getRandomQuoteRate(request.getTerm(),request.getAmount(),request.getConsumerRate());
			requestService.save(reqDB);

			// Send response		
			sendAResponseJob.doJob(reqDB);
		}
	}
	
	private Double getRandomQuoteRate(Integer termInMonth, double amount, Character rating) {
		
		rating = Character.toLowerCase(rating);
		
		double value = (termInMonth % 12);
		value += (amount % 1000);
		value += (rating - 'p') % 10;
		value += new Random().nextDouble() * 3;
		return value % 10;
	}
	

	private boolean isAutomode() {
		return configProperties.getProperty("bank.autorespond")
				.equalsIgnoreCase("true");
	}

	private boolean isValidRequest(ARequest request) {
		boolean validTerm = validatorUtils.isValidTerm(request.getTerm());
		boolean validAmount = validatorUtils.isValidAmount(request.getAmount());
		boolean validConsumerRate = validatorUtils
				.isValidConsumerRate(Character.toLowerCase(request
						.getConsumerRate()));
		return validTerm && validAmount && validConsumerRate;
	}
}
