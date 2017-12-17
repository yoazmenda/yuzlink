package com.klix.app.utils;


import com.beust.jcommander.Parameter;

public class CommandLineOptions {

    @Parameter(names = "--debug")
    public boolean debug = false;

    @Parameter(names = {"--service-port"})
    public Integer servicePort = 443;

    @Parameter(names = {"--database"})
    public String database = "klix";

    @Parameter(names = {"--db-host"})
    public String dbHost = "192.168.99.100"; //docker

    @Parameter(names = {"--db-username"})
    public String dbUsername = "klix_admin";

    @Parameter(names = {"--db-password"})
    public String dbPassword = "4488";

    @Parameter(names = {"--db-port"})
    public Integer dbPort = 5432;
}
