#Business Integration - SOAP Schufa

Diese Repository enthält zwei Projekte:

## soapSchufa

Ist ein Java Webanwendung welche in einem Servlet-Container wie dem Tomcat ausgeführt werden kann.
Die JMS Bank nimmt Messages entgegen, zeigt diese auf der Weboberfläche an und antwortet mit einer Message.

Für weitere Informationen siehe `soapSchufa` Projekt **README.md**. 

## soapSchufaJavaClient

Ist eine Java Anwendung welcher verwendet werden kann um Testnachrichten an die Webanwendung zu senden.

## Bauen der Anwendungen

* Die beiden Anwendungen wurden als Maven Projekte realisiert und können mit dem Befehl:

	$ mvn clean install
	
gebaut werden.

 

