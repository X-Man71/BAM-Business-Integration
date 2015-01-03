package de.hfu.vschufa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.jws.WebService;

@WebService(targetNamespace = "http://vschufa.hfu.de/", endpointInterface = "de.hfu.vschufa.IVSchufa", portName = "VSchufaPort", serviceName = "VSchufaService")
public class VSchufa implements IVSchufa {
	// Create random with time-seed
	private final Random rnd = new Random((new Date()).getTime());

	// List of known social security numbers
	private final Map<String, Integer> knownSSN = new HashMap<String, Integer>();

	/**
	 * Initalize default values for testing this web service.
	 */
	public VSchufa() {
		// Default values for specific social SecurityNumbers
		knownSSN.put("test-1", -1);
		knownSSN.put("test0", 0);
		knownSSN.put("test9999", 9999);
		knownSSN.put("test10000", 10000);

		// Rating - A
		knownSSN.put("testA", 9858 + rnd.nextInt(142));
		// Rating - B
		knownSSN.put("testB", 9752 + rnd.nextInt(106));
		// Rating - C
		knownSSN.put("testC", 9678 + rnd.nextInt(74));
		// Rating - D
		knownSSN.put("testD", 9580 + rnd.nextInt(98));
		// Rating - E
		knownSSN.put("testE", 9435 + rnd.nextInt(145));
		// Rating - F
		knownSSN.put("testF", 9196 + rnd.nextInt(239));
		// Rating - G
		knownSSN.put("testG", 8712 + rnd.nextInt(484));
		// Rating - H
		knownSSN.put("testH", 8140 + rnd.nextInt(572));
		// Rating - I
		knownSSN.put("testI", 7657 + rnd.nextInt(483));
		// Rating - J
		knownSSN.put("testJ", 7247 + rnd.nextInt(410));
		// Rating - K
		knownSSN.put("testK", 6545 + rnd.nextInt(702));
		// Rating - L
		knownSSN.put("testL", 4612 + rnd.nextInt(1932));
		// --
		// Rating - N
		knownSSN.put("testN", 3799 + rnd.nextInt(813));
		// Rating - O
		knownSSN.put("testO", 786 + rnd.nextInt(3013));
		// Rating - P
		knownSSN.put("testP", 1 + rnd.nextInt(784));
	}

	@Override
	public int getScoreValue(String socialSecurityNumber) {
		// When socialSecurityNumber is unknown return random number between 0
		// and 9999
		return knownSSN.containsKey(socialSecurityNumber) ? knownSSN
				.get(socialSecurityNumber) : rnd.nextInt(10000);
	}
}
