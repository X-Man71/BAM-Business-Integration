package embeddedwebserver;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * Can be used to start application with a lightwight Jetty webserver.
 * 
 * @author Christian Henle
 */
public class JettyServer 
{
	public static void main(String[] args) throws Exception 
	{
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		WebAppContext context = new WebAppContext();
		
		context.setContextPath("/jmsBankA");

		ResourceCollection resourceCollection = new ResourceCollection(
				new String[] { "src/main/webapp" });

		context.setBaseResource(resourceCollection);

		context.setExtraClasspath("target/classes");

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
		server.setHandler(handlers);

		server.start();
		server.join();
	}
}
