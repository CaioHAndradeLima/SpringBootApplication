package com.mechanical.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {


    @Autowired
    public DatabaseLoader() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
