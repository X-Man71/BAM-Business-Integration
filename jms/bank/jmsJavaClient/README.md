#jmsJavaClient

## Ausführen der Anwendung

Die Anwendung kann mit der Java Klasse TestSender im Package hs.furtwangen.bam.jms.sender.bankA gestartet werden, es wird dann eine Nachricht an das Topic requestBankA geschickt.
Soll eine Nachricht an das Topic requestBankB gesendet werden so kann die TestSender Klasse aus dem Package hs.furtwangen.bam.jms.sender.bankB verwendet werden.
Vor dem senden einer Nachricht ist es wichtig den Java Messaging Service zu starten.

## Bauen der Anwendung

Die Anwendung kann mit dem Befehl:

	$mvn clean install 

von Maven gebaut werden. 

## Konfiguration der Anwendung

Die JMS Einstellungen für die Anwendung können in der Klasse JMSUtils im Package hs.furtwangen.bam.jms.util vorgenommen werden.


 

