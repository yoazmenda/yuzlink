package com.klix.app;/*
 * This Java source file was generated by the Gradle 'init' task.
 */


import com.klix.app.link.LinkDao;
import spark.Spark;

public class Application {

    public static LinkDao linkDao;

    public static void main(String[] args) {

        Spark.staticFileLocation("/public");
        spark.Spark.init();

    }
}
