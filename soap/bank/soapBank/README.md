#soapBank

## Ausführen der Anwendung

Das Projekt enthält einen konfigurierten Jetty Server. Dieser ist im Package embeddedwebserver enthalten und kann als Java Anwendung mit der Klasse JettyServer gestartet werden.
Die Anwendung besitzt keine Benutzeroberfläche um zu prüfen ob die Anwendung korrekt gestartet wurde, kann die [WSDL](http://localhost:8080/VBankService/services/VBankPort?wsdl) angezeigt werden.

## Bauen der Anwendung

Die Anwendung kann mit dem Befehl:

	$mvn clean install 

von Maven gebaut werden. Die dadurch entstandene WAR kann in einem Tomcat Servlet-Container betrieben werden.
Der Name der WAR kann in der pom.xml geändert werden bsp. <war.name>soapBank</war.name>.

 

