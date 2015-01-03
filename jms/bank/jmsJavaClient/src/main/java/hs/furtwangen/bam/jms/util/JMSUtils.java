package hs.furtwangen.bam.jms.util;

import java.util.Hashtable;
import java.util.Map;

public class JMSUtils {
	
	public static final String APOLLO_USER = "KEY_APOLLO_USER";
	public static final String APOLLO_PASSWORD = "KEY_APOLLO_PASSWORD";
	public static final String APOLLO_HOST = "KEY_APOLLO_HOST";
	public static final String APOLLO_PORT = "KEY_APOLLO_PORT";
	

	public static final String LISTENER_TOPIC = "KEY_LISTENER_TOPIC";
	public static final String SENDER_TOPIC_A = "KEY_SENDER_TOPIC_A";
	public static final String SENDER_TOPIC_B = "KEY_SENDER_TOPIC_B";
	public static final String CONNECTION_FACTORY = "KEY_CONNECTION_FACTORY";

	public static Map<String,String> getProperties() {
		// Edit Settings
		Map<String,String> properties = new Hashtable<>();
		
		properties.put(APOLLO_USER, "user");
		properties.put(APOLLO_PASSWORD, "password");
		properties.put(APOLLO_HOST, "localhost");
		properties.put(APOLLO_PORT, "61613");
		

		properties.put(SENDER_TOPIC_A, "requestBankA");
		properties.put(SENDER_TOPIC_B, "requestBankB");
		properties.put(LISTENER_TOPIC, "responseBank");
		
		
		
		return properties;
	}
}
