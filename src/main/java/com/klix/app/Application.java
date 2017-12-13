package com.klix.app;/*
 * This Java source file was generated by the Gradle 'init' task.
 */


import com.klix.app.link.LinkDao;
import spark.Spark;

import static spark.Spark.post;


public class Application {

    public static LinkDao linkDao;

    public static void main(String[] args) {
        int port = 443;
        String portStr = System.getProperty("PORT");
        if (portStr != null && !portStr.equals("")) {
            port = Integer.valueOf(portStr);
        }


        System.out.println("Port: " + port);
        Spark.staticFileLocation("/public");

        spark.Spark.port(port);
        spark.Spark.init();


        post("/submit", (req, res) -> {
            return req.body();
        });

    }
}
