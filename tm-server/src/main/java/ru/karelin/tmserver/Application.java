package ru.karelin.tmserver;

import ru.karelin.tmserver.util.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;

public class Application {



    public static void main(String[] args)  {

        SeContainerInitializer.newInstance().initialize();
        Bootstrap bootstrap = CDI.current().select(Bootstrap.class).get();
        bootstrap.init();


    }
}
