package de.hs.furtwangen.bam.bi.jmsBank.jms.sender;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.stereotype.Component;

/**
 * Use {@link #sendObjectMessage(Serializable)} or
 * {@link #sendTextMessage(String)} for sending JMS Messages.
 * 
 * @author Christian Henle *
 */
@Component
public class MsgSender {

	private MessageProducer msgProducer = null;
	private Session session = null;

	@Resource(name = "configProperties")
	private Properties configProperties;

	public void registerNewMessageSender() throws NamingException, JMSException {

		// Hashtable properties = new JMSUtils().getProperties();
		Context context = new InitialContext(configProperties);

		ConnectionFactory factory = (ConnectionFactory) context
				.lookup(configProperties.getProperty("CONNECTION_FACTORY"));

		// Create Connection
		Connection connection = factory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = (Destination) context.lookup(configProperties
				.getProperty("SENDER_TOPIC"));

		// Create Consumer
		msgProducer = session.createProducer(destination);

		// start the connection to enable message delivery
		connection.start();
	}

	public void sendTextMessage(String text) throws JMSException {
		TextMessage msg = session.createTextMessage(text);
		msgProducer.send(msg);
	}

	public void sendObjectMessage(Serializable object) throws JMSException {
		ObjectMessage msg = session.createObjectMessage(object);
		msgProducer.send(msg);
	}
}
