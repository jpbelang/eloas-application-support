package ca.eloas.launcher;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

import java.io.File;

public class Launcher {

    private static final int DEFAULT_PORT = 8080;
    private Server server;
    private int port;

    private static String defaultConfigRoot = "src/test/webapp/WEB-INF";


    public Launcher(int port, String[] args)throws Exception{
        this.port = port;
        CommandLine cl = getOptions(args);
        String webapp = cl.getOptionValue("j", "src/test/webapp-test/WEB-INF/jetty-web.xml");
        String jetty = cl.getOptionValue("s", "src/test/webapp-test/WEB-INF/jetty.xml");
        String featuresString = cl.getOptionValue("f", null);

        buildServer(jetty, webapp, parseArgs(cl.getArgs()));

            if ( featuresString != null )  {

            for (String feature : featuresString.split(",")) {
                withFeature(feature);
            }
        }
    }

    public Launcher(String[] args) throws Exception {
        this(DEFAULT_PORT, args);
    }
    
    private void buildServer(String jettyConf, String webappConf, WebAppContext[] webapps) throws Exception {

        server = new Server(port);
        XmlConfiguration serverConf = new XmlConfiguration(new File(jettyConf).toURI().toURL().openStream());
        serverConf.configure(server);

        HandlerCollection handlers = new HandlerCollection();

        XmlConfiguration conf = new XmlConfiguration(new File(webappConf).toURI().toURL().openStream());
        for (WebAppContext webapp : webapps) {

            handlers.addHandler(webapp);
        }
        server.setHandler(handlers);

        for (WebAppContext webapp : webapps) {

            conf.configure(webapp);
        }


    }

    private CommandLine getOptions(String[] commandLine) throws ParseException {

        Options opts = new Options();
        opts.addOption(
                new Option("j", "jetty-web", true, "jetty-web.xml location")
        );
        opts.addOption(
                new Option("s", "jetty-server", true, "jetty.xml location")
                );
        opts.addOption(
                new Option("f", "feature", true, "features")
        );

        GnuParser gp = new GnuParser();
        return gp.parse(opts, commandLine);
    }

    private static WebAppContext[] parseArgs(String[] contextRoots) {
        int a = 0;
        WebAppContext[] contexts = new WebAppContext[contextRoots.length];
        for (String contextRoot : contextRoots) {

            String[] info = contextRoot.split("\\:");
            contexts[a] = new WebAppContext(info[0], info[1]);
            a++;
        }
        return contexts;
    }

    public Launcher(WebAppContext... webapps) throws Exception {

        buildServer("src/test/webapp/WEB-INF/jetty.xml", "src/test/webapp/WEB-INF/jetty-web.xml", webapps);
    }

    public Launcher withFeature(String name) throws Exception {

        if ( name.indexOf('/') == -1) {

            XmlConfiguration conf = new XmlConfiguration(new File(defaultConfigRoot, name + ".xml").toURI().toURL().openStream());
            conf.configure(server);
        } else {

            XmlConfiguration conf = new XmlConfiguration(new File(name).toURI().toURL().openStream());
            conf.configure(server);
        }

        return this;
    }

    public int launch() throws Exception {
        server.start();
        return port;
    }

    public void stop() throws Exception {

        server.stop();
    }
}
