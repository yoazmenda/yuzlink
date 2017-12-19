package com.klix.app;

import com.beust.jcommander.JCommander;
import com.klix.app.db.KlixModel;
import com.klix.app.db.Model;
import com.klix.app.services.link.RedirectHandler;
import com.klix.app.services.link.ShortenHandler;
import com.klix.app.utils.CommandLineOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import spark.Spark;

import static spark.Spark.*;


public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);
        log.info("Options.debug = " + options.debug);
        log.info("Options.database = " + options.database);
        log.info("Options.dbHost = " + options.dbHost);
        log.info("Options.dbUsername = " + options.dbUsername);
        log.info("Options.dbPort = " + options.dbPort);
        log.info("Options.servicePort = " + options.servicePort);

        port(options.servicePort);


        Sql2o sql2o = new Sql2o(
                "jdbc:postgresql://" + options.dbHost + ":" + options.dbPort + "/" + options.database,
                options.dbUsername,
                options.dbPassword);

        Model model = new KlixModel(sql2o);

        /***********************/

        Spark.staticFileLocation("/public");


        ShortenHandler shortenHandler = new ShortenHandler(model, options.serviceHost, options.servicePort);
        RedirectHandler redirectHandler = new RedirectHandler(model, options.serviceHost, options.servicePort);
        post("/shorten", shortenHandler);
        get("/:id", redirectHandler);
    }
}
