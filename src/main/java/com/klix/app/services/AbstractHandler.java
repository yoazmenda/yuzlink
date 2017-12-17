package com.klix.app.services;

import com.klix.app.db.Model;
import spark.Route;

public abstract class AbstractHandler implements Route {

    protected Model model;

    public AbstractHandler(Model model) {
        this.model = model;
    }
}