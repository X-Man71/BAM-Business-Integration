package hs.furtwangen.bam.jms.sender.bankA;

import hs.furtwangen.bam.jms.util.JMSUtils;

import java.io.Serializable;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MsgSender {

	// INSTANCE
	private static MsgSender INSTANCE = null;

	private MessageProducer msgProducer = null;
	private Session session = null;

	public synchronized static MsgSender getInstance() throws JMSException,
			NamingException {
		if (INSTANCE == null) {
			INSTANCE = new MsgSender();
		}
		return INSTANCE;
	}

	private MsgSender() throws NamingException, JMSException {
		registerNewMessageSender();
	}

	private void registerNewMessageSender() throws NamingException,
			JMSException {
		
		Hashtable<String,String> properties = JMSUtils.getProperties();

		Context context = new InitialContext(properties);

		ConnectionFactory factory = (ConnectionFactory) context
				.lookup(properties.get(JMSUtils.CONNECTION_FACTORY).toString());

		// Create Connection
		Connection connection = factory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = (Destination) context.lookup(properties.get(
				JMSUtils.SENDER_TOPIC_A).toString());

		// Create Consumer
		msgProducer = session.createProducer(destination);

		// start the connection to enable message delivery
		connection.start();
	}
	
	public void sendObjectMessage(Serializable aRequest) throws JMSException {
		ObjectMessage objectMessage = session.createObjectMessage(aRequest);
		msgProducer.send(objectMessage);
	}

}
