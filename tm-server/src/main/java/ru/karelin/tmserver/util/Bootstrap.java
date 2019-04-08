package ru.karelin.tmserver.util;


import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.*;
import ru.karelin.tmserver.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.*;
import ru.karelin.tmserver.service.*;

import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public final class Bootstrap implements ServiceLocator {

    private static final String USER_ENDPOINT_URL = "http://localhost:8080/UserEndpoint?wsdl";
    private static final String PROJECT_ENDPOINT_URL = "http://localhost:8080/ProjectEndpoint?wsdl";
    private static final String TASK_ENDPOINT_URL = "http://localhost:8080/TaskEndpoint?wsdl";
    private static final String DOMAIN_ENDPOINT_URL = "http://localhost:8080/DomainEndpoint?wsdl";
    private static final String SESSION_ENDPOINT_URL = "http://localhost:8080/SessionEndpoint?wsdl";


    @NotNull
    private ProjectService projectService;
    @NotNull
    private TaskService taskService;
    @NotNull
    private UserService userService;
    @NotNull
    private DomainService domainService;
    @NotNull
    private SessionService sessionServiceImpl;


    @NotNull
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @NotNull
    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @NotNull
    public DomainService getDomainService() {
        return domainService;
    }

    @Override
    @NotNull
    public UserService getUserService() {
        return userService;
    }

    @Override
    @NotNull
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    @NotNull
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public void init() throws SQLException, IOException, ClassNotFoundException {
        EntityManagerFactory entityManagerFactory = HiberInit.getEntityManagerFactory();
        projectService = new ProjectServiceImpl(entityManagerFactory);
        taskService = new TaskServiceImpl(entityManagerFactory);
        userService = new UserServiceImpl(entityManagerFactory);
        domainService = new DomainServiceImpl(entityManagerFactory);
        sessionServiceImpl = new SessionServiceImpl(entityManagerFactory);

        sessionServiceImpl.removeOldSessions(15); // clears old sessions from DB


        Endpoint.publish(USER_ENDPOINT_URL, new UserEndpoint(userService, sessionServiceImpl));
        System.out.println("Endpoint with url " + USER_ENDPOINT_URL + " started.");
        Endpoint.publish(PROJECT_ENDPOINT_URL, new ProjectEndpoint(projectService, sessionServiceImpl));
        System.out.println("Endpoint with url " + PROJECT_ENDPOINT_URL + " started.");
        Endpoint.publish(TASK_ENDPOINT_URL, new TaskEndpoint(taskService, sessionServiceImpl));
        System.out.println("Endpoint with url " + TASK_ENDPOINT_URL + " started.");
        Endpoint.publish(DOMAIN_ENDPOINT_URL, new DomainEndpoint(domainService, sessionServiceImpl));
        System.out.println("Endpoint with url " + DOMAIN_ENDPOINT_URL + " started.");
        Endpoint.publish(SESSION_ENDPOINT_URL, new SessionEndpoint(sessionServiceImpl));
        System.out.println("Endpoint with url " + SESSION_ENDPOINT_URL + " started");


        // create two users block
        //userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        //userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block


    }


}
