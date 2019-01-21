package com.mechanical;

import com.mechanical.model.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
@ComponentScan(basePackages = "com.mechanical.endpoint")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void test() {
        UserModel userModel = new UserModel("nome","email","senha");
        List<UserModel> a = new ArrayList();
        a.add( userModel );
    }
}
