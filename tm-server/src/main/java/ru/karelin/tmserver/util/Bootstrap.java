package ru.karelin.tmserver.util;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.*;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.repository.*;
import ru.karelin.tmserver.service.*;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.sql.Connection;
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
    private SessionService sessionService;


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
        Connection connection = DbConnector.init();
        @NotNull final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        @NotNull final TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        @NotNull final MD5Generator md5Generator = new MD5Generator();
        //@NotNull final UserRepository userRepository = new UserRepositoryImpl();
        @NotNull final UserRepository userRepository = new UserRepositoryJdbc(connection);
        userService = new UserServiceImpl(md5Generator, userRepository);
        domainService = new DomainServiceImpl(userRepository, taskRepository, projectRepository);
        @NotNull SessionRepository sessionRepository = new SessionRepositoryImpl();
        sessionService = new SessionService(sessionRepository, userRepository);


        Endpoint.publish(USER_ENDPOINT_URL, new UserEndpoint(userService,sessionService));
        System.out.println("Endpoint with url " + USER_ENDPOINT_URL + " started.");
        Endpoint.publish(PROJECT_ENDPOINT_URL, new ProjectEndpoint(projectService, sessionService));
        System.out.println("Endpoint with url " + PROJECT_ENDPOINT_URL + " started.");
        Endpoint.publish(TASK_ENDPOINT_URL, new TaskEndpoint(taskService, sessionService));
        System.out.println("Endpoint with url " + TASK_ENDPOINT_URL + " started.");
        Endpoint.publish(DOMAIN_ENDPOINT_URL, new DomainEndpoint(domainService, sessionService));
        System.out.println("Endpoint with url " + DOMAIN_ENDPOINT_URL + " started.");
        Endpoint.publish(SESSION_ENDPOINT_URL, new SessionEndpoint(sessionService));
        System.out.println("Endpoint with url " + SESSION_ENDPOINT_URL + " started");


        // create two users block
        //userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        //userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block


    }


}
