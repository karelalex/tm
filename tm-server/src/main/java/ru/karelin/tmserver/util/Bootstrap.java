package ru.karelin.tmserver.util;


import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.SessionEndpoint;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.UserEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    private static final String USER_ENDPOINT_URL = "http://localhost:8080/UserEndpoint?wsdl";
    private static final String PROJECT_ENDPOINT_URL = "http://localhost:8080/ProjectEndpoint?wsdl";
    private static final String TASK_ENDPOINT_URL = "http://localhost:8080/TaskEndpoint?wsdl";
    private static final String SESSION_ENDPOINT_URL = "http://localhost:8080/SessionEndpoint?wsdl";

    @Inject
    private SessionService sessionService ;

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    ProjectEndpoint projectEndpoint;

    @Inject
    TaskEndpoint taskEndpoint;

    @Inject
    SessionEndpoint sessionEndpoint;


    @NotNull
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Override
    public void init() {

        sessionService.removeOldSessions(15); // clears old sessions from DB


        Endpoint.publish(USER_ENDPOINT_URL, userEndpoint);
        System.out.println("Endpoint with url " + USER_ENDPOINT_URL + " started.");
        Endpoint.publish(PROJECT_ENDPOINT_URL, projectEndpoint);
        System.out.println("Endpoint with url " + PROJECT_ENDPOINT_URL + " started.");
        Endpoint.publish(TASK_ENDPOINT_URL, taskEndpoint);
        System.out.println("Endpoint with url " + TASK_ENDPOINT_URL + " started.");
        Endpoint.publish(SESSION_ENDPOINT_URL, sessionEndpoint);
        System.out.println("Endpoint with url " + SESSION_ENDPOINT_URL + " started");


        // create two users block
        //userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        //userService.registerNewUser("zz", "tt".toCharArray(), "tank", RoleType.ORDINARY_USER);
        // userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block


    }


}
