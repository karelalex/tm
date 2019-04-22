package ru.karelin.tmserver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.karelin.tmserver.config.MainConfig;
import ru.karelin.tmserver.util.Bootstrap;

public class Application {


    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Bootstrap bootstrap = context.getBean(Bootstrap.class);
        bootstrap.init();


    }
}
