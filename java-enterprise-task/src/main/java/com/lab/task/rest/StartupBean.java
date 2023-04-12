package com.lab.task.rest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartupBean {
    @PostConstruct
    public void hello() {
        System.out.println("App is running");
    }
}
