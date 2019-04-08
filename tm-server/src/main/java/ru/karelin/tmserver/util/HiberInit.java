package ru.karelin.tmserver.util;


import lombok.SneakyThrows;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HiberInit {
    @SneakyThrows
    public static EntityManagerFactory getEntityManagerFactory() {
        Properties properties = new Properties();
        properties.load(HiberInit.class.getResourceAsStream("/db.properties"));
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, properties.getProperty("driver"));
        settings.put(Environment.URL, properties.getProperty("url"));
        settings.put(Environment.USER, properties.getProperty("user"));
        settings.put(Environment.PASS, properties.getProperty("password"));
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.FORMAT_SQL, "true");
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();

    }


}
