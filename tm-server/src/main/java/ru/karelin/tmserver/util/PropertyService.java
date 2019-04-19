package ru.karelin.tmserver.util;

import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Properties;

@ApplicationScoped
@Named

public class PropertyService {
    Properties properties = new Properties();

    PropertyService() {
        init();
    }

    @SneakyThrows
    private void init() {
        properties.load(PropertyService.class.getResourceAsStream("/app.properties"));
        String temp = System.getProperty("port");
        System.out.println(temp);
        if (temp != null && !temp.isEmpty()) {
            properties.setProperty("app.port", temp);
        }
        temp = System.getProperty("host");
        if (temp != null && !temp.isEmpty()) {
            properties.setProperty("app.host", temp);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
