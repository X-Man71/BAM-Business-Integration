package de.hs.furtwangen.bam.bi.jmsBank.jms.listener;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import jms.model.ARequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hs.furtwangen.bam.bi.jmsBank.job.HandleARequestJob;
/**
 * Receives JMS Messages with the {@link #onMessage(Message)} Methode.
 * Two types of Messages can be processed {@link TextMessage} and {@link ARequest} all other are ignored.
 * 
 * @author Christian Henle
 *
 */
@Component
public class MsgListener implements MessageListener {
	
	@Autowired
	private HandleARequestJob handleARequestJob;

	@Override
	public void onMessage(Message message) {
		// TextMessage
		if (message instanceof TextMessage) {

			try {
				TextMessage textMsg = (TextMessage) message;
				System.out.println("Received Text-Message: "
						+ textMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
			return;
		}

		// ARequest
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage objMsg = (ObjectMessage) message;
				Serializable object = objMsg.getObject();
				if (object instanceof ARequest) {
					handleRequest((ARequest) object);
				} else {
					// Ignore if it insn't a valid RequestObject for this Bank
					System.out.println("Recieved an unkown ObjectMessage: "
							+ object.getClass().getName());
				}
			} catch (JMSException e) {
				System.out.println(e.getErrorCode());
			}
			return;
		}
	}

	private void handleRequest(ARequest request) {
		
		try {
			handleARequestJob.doJob(request);
		} catch (Exception e) {
			System.out.println("handleRequest "+ e.getClass()+" "+e.getMessage());

		};
	}
}
