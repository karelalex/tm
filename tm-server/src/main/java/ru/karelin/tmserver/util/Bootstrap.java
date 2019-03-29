package ru.karelin.tmserver.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.UserEndpoint;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.repository.ProjectRepositoryImpl;
import ru.karelin.tmserver.repository.TaskRepositoryImpl;
import ru.karelin.tmserver.repository.UserRepositoryImpl;
import ru.karelin.tmserver.service.DomainServiceImpl;
import ru.karelin.tmserver.service.ProjectServiceImpl;
import ru.karelin.tmserver.service.TaskServiceImpl;
import ru.karelin.tmserver.service.UserServiceImpl;

import javax.xml.ws.Endpoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public final class Bootstrap implements ServiceLocator {

    private static final String QUIT = "exit";
    private static final String USER_ENDPOINT_URL = "http://localhost:8080/UserEndpoint?wsdl";
    private static final String PROJECT_ENDPOINT_URL = "http://localhost:8080/ProjectEndpoint?wsdl";
    private static final String TASK_ENDPOINT_URL = "http://localhost:8080/TaskEndpoint?wsdl";
    private static final String DOMAIN_ENDPOINT_URL = "http://localhost:8080/DomainEndpoint?wsdl";


    @NotNull
    private ProjectService projectService;
    @NotNull
    private TaskService taskService;
    @NotNull
    private UserService userService;
    @NotNull
    private DomainService domainService;


    @NotNull
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Nullable
    private User currentUser;

    @Override
    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(@Nullable User currentUser) {
        this.currentUser = currentUser;
    }

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
    public void init() {
        @NotNull final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        @NotNull final TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        @NotNull final MD5Generator md5Generator = new MD5Generator();
        @NotNull final UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(md5Generator, userRepository);
        domainService = new DomainServiceImpl(userRepository, taskRepository, projectRepository);

        Endpoint.publish(USER_ENDPOINT_URL, new UserEndpoint(userService));
        System.out.println("Endpoint with url " + USER_ENDPOINT_URL + " started.");
        Endpoint.publish(PROJECT_ENDPOINT_URL, new ProjectEndpoint(projectService));
        System.out.println("Endpoint with url " + PROJECT_ENDPOINT_URL + " started.");
        Endpoint.publish(TASK_ENDPOINT_URL, new TaskEndpoint(taskService));
        System.out.println("Endpoint with url " + TASK_ENDPOINT_URL + " started.");
        Endpoint.publish(DOMAIN_ENDPOINT_URL, new DomainEndpoint(domainService));
        System.out.println("Endpoint with url " + DOMAIN_ENDPOINT_URL + " started.");
        //command registration block


        //end of command registration block

        // create two users block
        userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block


    }


}
