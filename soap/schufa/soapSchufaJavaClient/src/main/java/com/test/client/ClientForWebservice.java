package com.test.client;

import com.test.IVSchufa;
import com.test.VSchufaService;

public class ClientForWebservice {
	public static void main(String[] args) {
		IVSchufa ivSchufa = new VSchufaService().getVSchufaPort();

		String securitySocialNumber = "testA";
		Integer scoreValue = ivSchufa.getScoreValue(securitySocialNumber);
		System.out.println("scoreValue " + scoreValue);
	}

}
