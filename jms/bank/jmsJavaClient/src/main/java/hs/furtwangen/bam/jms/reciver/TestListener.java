package hs.furtwangen.bam.jms.reciver;

import hs.furtwangen.bam.jms.reciver.MsgListener;

import javax.jms.JMSException;
import javax.naming.NamingException;


public class TestListener {

	public static void main(String[] args) {
		try {
			MsgListener.registerNewMessageListener();
			System.out.println("JMS: MsgListener is listening...");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
