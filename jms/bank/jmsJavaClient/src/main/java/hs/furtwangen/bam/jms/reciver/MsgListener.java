package hs.furtwangen.bam.jms.reciver;

import hs.furtwangen.bam.jms.util.JMSUtils;

import java.io.Serializable;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import jms.model.AResponse;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;


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
		
		Map<String, String> properties = JMSUtils.getProperties();
		String apollo_host = properties.get(JMSUtils.APOLLO_HOST);
		int apollo_port = Integer.parseInt(properties.get(JMSUtils.APOLLO_PORT));
		String apollo_user = properties.get(JMSUtils.APOLLO_USER);
		String apollo_password = properties.get(JMSUtils.APOLLO_PASSWORD);

		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(apollo_host,
				apollo_port, apollo_user, apollo_password);

		// Create Connection
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = new TopicImpl("topic://"
				+ properties.get(JMSUtils.LISTENER_TOPIC).toString());
	
		// Create Consumer
		MessageConsumer msgListener = session.createConsumer(destination);
		msgListener.setMessageListener(new MsgListener());

		// start the connection to enable message delivery
		connection.start();
	}

}
