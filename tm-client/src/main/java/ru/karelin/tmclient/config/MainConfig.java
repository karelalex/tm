package ru.karelin.tmclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.karelin.tmserver.endpoint.*;

@Configuration
@ComponentScan(value = "ru.karelin.tmclient")
public class MainConfig {

    @Bean
    public UserEndpoint userEndpoint(){
        return new UserEndpointService().getUserEndpointPort();
    }

    @Bean
    public ProjectEndpoint projectEndpoint(){
        return new ProjectEndpointService().getProjectEndpointPort();
    }

    @Bean
    public SessionEndpoint sessionEndpoint(){
        return new SessionEndpointService().getSessionEndpointPort();
    }

    @Bean
    public TaskEndpoint taskEndpoint(){
        return new TaskEndpointService().getTaskEndpointPort();
    }
}
