#soapSchufaJavaClient

## Ausführen der Anwendung

Die Anwendung kann mit der Java Klasse ClientForWebservice im Package com.test.client gestartet werden, es wird eine SOAP Webservice der Bank aufgerufen.
Der Webservice liefert einen Zinssatz zurück, dieser wird von Client auf der Console ausgegeben.

## Bauen der Anwendung

Die Anwendung kann mit dem Befehl:

	$mvn clean install 

von Maven gebaut werden. 

## Konfiguration der Anwendung

Die JMS Einstellungen für die Anwendung können in der Klasse JMSUtils im Package hs.furtwangen.bam.jms.util vorgenommen werden.


 

