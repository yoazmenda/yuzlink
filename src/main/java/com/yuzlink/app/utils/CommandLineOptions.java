package com.yuzlink.app.utils;


import com.beust.jcommander.Parameter;

public class CommandLineOptions {

    @Parameter(names = "--service-host")
    public String serviceHost = "localhost";

    @Parameter(names = {"--service-port"})
    public Integer servicePort = 443;

    @Parameter(names = "--debug")
    public boolean debug = false;

    @Parameter(names = {"--database"})
    public String database = "yuzlink";

    @Parameter(names = {"--db-host"})
    public String dbHost = "192.168.99.100"; //docker

    @Parameter(names = {"--db-username"})
    public String dbUsername = "yuzlink_admin";

    @Parameter(names = {"--db-password"})
    public String dbPassword = "4488";

    @Parameter(names = {"--db-port"})
    public Integer dbPort = 5432;
}
