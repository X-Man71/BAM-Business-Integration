package hs.furtwangen.bam.jms.reciver;

import hs.furtwangen.bam.jms.util.JMSUtils;

import java.io.Serializable;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jms.model.AResponse;


public class MsgListener implements MessageListener {

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
		
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage objMsg = (ObjectMessage) message;
				Serializable object = objMsg.getObject();
				if (object instanceof AResponse) {
					AResponse aResponse = (AResponse) object;
					System.out.println("BankName "+aResponse.getBankName()+ " QuoteRate "+aResponse.getQuoteRate()+" getRequestId "+aResponse.getRequestId());
				} else {
					// Ignore if it insn't a valid RequestObject for this Bank
					System.out.println("Recieved an unkown ObjectMessage: "
							+ object.getClass().getName());
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
	static void registerNewMessageListener() throws NamingException,
	JMSException {
		
		Hashtable<String,String> properties = JMSUtils.getProperties();

		Context context = new InitialContext(properties);

		ConnectionFactory factory = (ConnectionFactory) context
				.lookup(properties.get(JMSUtils.CONNECTION_FACTORY).toString());

		// Create Connection
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = (Destination) context.lookup(properties.get(
				JMSUtils.LISTENER_TOPIC).toString());

		// Create Consumer
		MessageConsumer msgListener = session.createConsumer(destination);
		msgListener.setMessageListener(new MsgListener());

		// start the connection to enable message delivery
		connection.start();
	}

}
