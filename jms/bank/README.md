#Business Integration - JMS Bank

Diese Repository enthält zwei Projekte:

## jmsBank

Ist ein Java Webanwendung welche in einem Servlet-Container wie dem Tomcat ausgeführt werden kann.
Die JMS Bank nimmt Messages entgegen, zeigt diese auf der Weboberfläche an und antwortet mit einer Message.

Für weitere Informationen siehe `jmsBank` Projekt **README.md**. 

## jmsJavaClient

Ist eine Java Anwendung welcher verwendet werden kann um Testnachrichten an die Webanwendung zu senden.

## Bauen der Anwendungen

* Die beiden Anwendungen wurden als Maven Projekte realisiert und können mit dem Befehl:

	$ mvn clean install
	
gebaut werden.

 

