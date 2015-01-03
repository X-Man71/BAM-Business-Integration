#jmsBank

## Ausführen der Anwendung

Das Projekt enthält einen konfigurierten Jetty Server. Dieser ist im Package embeddedwebserver enthalten und kann als Java Anwendung mit der Klasse JettyServer gestartet werden.
Nach dem starten ist die [Benutzeroberfläche](http://localhost:8080/jmsBankA/) der Webanwendung erreichbar.
Für das starten der Anwendung, ist es wichtig, dass zuerst das JMS gestartet wird!

## Bauen der Anwendung

Die Anwendung kann mit dem Befehl:

	$mvn clean install 

von Maven gebaut werden. Die dadurch entstandene WAR kann in einem Tomcat Servlet-Container betrieben werden.
Der Name der WAR kann in der pom.xml geändert werden bsp. <war.name>jmsBankA</war.name> oder <war.name>jmsBankB</war.name>.

## Konfiguration der Anwendung

Dieses Projekt kann auch verwendet werden um die JMSBankB zu bauen.
Die notwendigen Änderungen können an der config.properties durchgeführt werden, diese ist im Ordner src/main/resources zu finden.
Es kann festgelegt werden, welchen Bankname die Anwendung in seiner Antwort mitteilt, die JMS konfiguration kann verändert werden 
und die Grenzen für Kreditanfragen können angepasst werden.

 

