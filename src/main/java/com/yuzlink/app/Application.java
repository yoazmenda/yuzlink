package com.yuzlink.app;

import com.beust.jcommander.JCommander;
import com.yuzlink.app.db.ModelImpl;
import com.yuzlink.app.db.Model;
import com.yuzlink.app.handlers.GetUrlsHandler;
import com.yuzlink.app.handlers.RedirectHandler;
import com.yuzlink.app.handlers.ShortenHandler;
import com.yuzlink.app.utils.CommandLineOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import spark.Spark;


import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

//import static spark.Spark.*;


public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws URISyntaxException {
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);
        log.info("Options.debug = " + options.debug);
        log.info("Options.database = " + options.database);
        log.info("Options.dbHost = " + options.dbHost);
        log.info("Options.dbUsername = " + options.dbUsername);
        log.info("Options.dbPort = " + options.dbPort);
        log.info("Options.servicePort = " + options.servicePort);

        port(options.servicePort);

        String username = options.dbUsername;
        String password = options.dbPassword;
        String dbUrl = "jdbc:postgresql://" + options.dbHost + ":" + options.dbPort + "/" + options.database;

        String dbUriString = System.getenv("DATABASE_URL");


        if (dbUriString != null){
            URI dbUri = new URI(dbUriString);
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        }

        Sql2o sql2o = new Sql2o(
                dbUrl,
                username,
                password);

        Model model = new ModelImpl(sql2o);

        /*****    Routes       ******/
        Spark.staticFileLocation("/public");
        get("/:id", new RedirectHandler(model, options.serviceHost, options.servicePort));
        post("/shorten", new ShortenHandler(model, options.serviceHost, options.servicePort));

    }
}
