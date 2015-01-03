package de.hfu.vschufa;

import javax.jws.WebService;

@WebService(name = "IVSchufa", targetNamespace = "http://vschufa.hfu.de/")
public interface IVSchufa {

	public int getScoreValue(String socialSecurityNumber);

}