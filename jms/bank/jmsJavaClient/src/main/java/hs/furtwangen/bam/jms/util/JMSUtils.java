package hs.furtwangen.bam.jms.util;

import java.util.Hashtable;

import javax.naming.Context;

public class JMSUtils {

	public static final String LISTENER_TOPIC = "KEY_LISTENER_TOPIC";
	public static final String SENDER_TOPIC_A = "KEY_SENDER_TOPIC_A";
	public static final String SENDER_TOPIC_B = "KEY_SENDER_TOPIC_B";
	public static final String CONNECTION_FACTORY = "KEY_CONNECTION_FACTORY";

	public static Hashtable<String,String> getProperties() {
		Hashtable<String,String> properties = new Hashtable<String,String>();
		properties.put(CONNECTION_FACTORY, "ConnectionFactory");
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.exolab.jms.jndi.InitialContextFactory");

		// Edit Settings
		//properties.put(Context.PROVIDER_URL, "tcp://192.168.56.101:3035/");
		properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
		properties.put(SENDER_TOPIC_A, "requestBankA");
		properties.put(SENDER_TOPIC_B, "requestBankB");
		properties.put(LISTENER_TOPIC, "responseBank");
		return properties;
	}
}
