package hs.furtwangen.bam.jms.sender.bankA;

import hs.furtwangen.bam.jms.util.JMSUtils;

import java.io.Serializable;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import jms.model.ARequest;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;

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

		Map<String, String> properties = JMSUtils.getProperties();
		String apollo_host = properties.get(JMSUtils.APOLLO_HOST);
		int apollo_port = Integer.parseInt(properties.get(JMSUtils.APOLLO_PORT));
		String apollo_user = properties.get(JMSUtils.APOLLO_USER);
		String apollo_password = properties.get(JMSUtils.APOLLO_PASSWORD);

		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(apollo_host,
				apollo_port, apollo_user, apollo_password);

		// Create Connection
		Connection connection = factory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = new TopicImpl("topic://"
				+ properties.get(JMSUtils.SENDER_TOPIC_A).toString());

		// Create Consumer
		msgProducer = session.createProducer(destination);

		// start the connection to enable message delivery
		connection.start();
	}

	public void sendObjectMessage(ARequest aRequest) throws JMSException {
		Serializable serializable = (ARequest) aRequest;
		ObjectMessage objectMessage = session.createObjectMessage(serializable);
		msgProducer.send(objectMessage);
	}
	
	public void sendTextMessage(String text) throws JMSException {
		TextMessage objectMessage = session.createTextMessage(text);
		msgProducer.send(objectMessage);
	}

}
