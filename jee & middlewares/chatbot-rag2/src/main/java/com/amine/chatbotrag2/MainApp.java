package com.amine.chatbotrag2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
//@Theme("my-theme")
public class MainApp implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
