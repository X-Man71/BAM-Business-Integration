package de.hs.furtwangen.bam.bi.jmsBank.job;

import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.NamingException;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hs.furtwangen.bam.bi.jmsBank.jms.listener.MsgListener;

@Component
public class JMSListenerStart {

	private static MessageConsumer msgListener = null;
	
	@Autowired
	private MsgListener listener;
	
	@Resource(name = "configProperties")
	private Properties configProperties;


	public void startJMSListening() throws Exception {
		if (msgListener == null) {
			registerNewMessageListener();
			System.out.println("JMS: MsgListener is listening...");
		}

	}

	private void registerNewMessageListener() throws NamingException,
			JMSException {
		
		String apollo_host = configProperties.getProperty("APOLLO_HOST");
		int apollo_port = Integer.parseInt(configProperties.getProperty("APOLLO_PORT"));
		String apollo_user = configProperties.getProperty("APOLLO_USER");
		String apollo_password = configProperties.getProperty("APOLLO_PASSWORD");
		
		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(apollo_host, apollo_port, apollo_user, apollo_password);

		// Create Connection
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Destination is a topic
		Destination destination = new TopicImpl("topic://"+configProperties.getProperty("LISTENER_TOPIC"));

		// Create Consumer
		MessageConsumer msgListener = session.createConsumer(destination);
		msgListener.setMessageListener(newMessageListener());

		// start the connection to enable message delivery
		connection.start();
	}

	private MessageListener newMessageListener() {
		return listener;
	}

}
