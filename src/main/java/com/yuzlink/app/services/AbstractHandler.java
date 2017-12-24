package com.yuzlink.app.services;

import com.yuzlink.app.db.Model;
import spark.Route;

public abstract class AbstractHandler implements Route {

    protected Model model;

    public AbstractHandler(Model model) {
        this.model = model;
    }
}