package ru.karelin.tmserver.util;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.SessionEndpoint;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.UserEndpoint;

import javax.xml.ws.Endpoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class Bootstrap implements ServiceLocator {

    private static final String USER_ENDPOINT_URL = "UserEndpoint?wsdl";
    private static final String PROJECT_ENDPOINT_URL = "ProjectEndpoint?wsdl";
    private static final String TASK_ENDPOINT_URL = "TaskEndpoint?wsdl";
    private static final String SESSION_ENDPOINT_URL = "SessionEndpoint?wsdl";



    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserEndpoint userEndpoint;

    @Autowired
    ProjectEndpoint projectEndpoint;

    @Autowired
    TaskEndpoint taskEndpoint;

    @Autowired
    SessionEndpoint sessionEndpoint;

    @Autowired
    Environment environment;


    @NotNull
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Override
    public void init() {

        sessionService.removeOldSessions(15); // clears old sessions from DB

        @NotNull final String HOST_PORT = "http://" + environment.getProperty("host") + ":" + environment.getProperty("port") + "/";
        Endpoint.publish(HOST_PORT + USER_ENDPOINT_URL, userEndpoint);
        System.out.println("Endpoint with url " + HOST_PORT + USER_ENDPOINT_URL + " started.");
        Endpoint.publish(HOST_PORT + PROJECT_ENDPOINT_URL, projectEndpoint);
        System.out.println("Endpoint with url " + HOST_PORT + PROJECT_ENDPOINT_URL + " started.");
        Endpoint.publish(HOST_PORT + TASK_ENDPOINT_URL, taskEndpoint);
        System.out.println("Endpoint with url " + HOST_PORT + TASK_ENDPOINT_URL + " started.");
        Endpoint.publish(HOST_PORT + SESSION_ENDPOINT_URL, sessionEndpoint);
        System.out.println("Endpoint with url " + HOST_PORT + SESSION_ENDPOINT_URL + " started");


        // create two users block

        //userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        //userService.registerNewUser("zz", "tt".toCharArray(), "tank", RoleType.ORDINARY_USER);
        //userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block


    }


}
