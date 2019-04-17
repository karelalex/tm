package ru.karelin.tmclient;

import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Category(CrudIntegration.class)
public class TaskAndProjectTesting {
    private static ProjectEndpoint projectEndpoint;
    private static SessionEndpoint sessionEndpoint;
    private static DateConverter dateConverter;
    private static UserEndpoint userEndpoint;
    private static SessionDto currentSession = null;
    private static TaskEndpoint taskEndpoint;
    private static SeContainer container;
    private static DatatypeFactory factory;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @BeforeClass
    public static void MainInit() {
        container = SeContainerInitializer.newInstance().initialize();
        userEndpoint = CDI.current().select(UserEndpoint.class).get();
        sessionEndpoint = CDI.current().select(SessionEndpoint.class).get();
        projectEndpoint = CDI.current().select(ProjectEndpoint.class).get();
        taskEndpoint = CDI.current().select(TaskEndpoint.class).get();
        dateConverter = CDI.current().select(DateConverter.class).get();
        if (!userEndpoint.isUserExistsByLogin("testUser")) {
            userEndpoint.registerNewUser("testUser", "testPass", "Test User");
        }
    }

    @Before
    public void endPointInit() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        currentSession = sessionEndpoint.login("testUser", "testPass");
        projectEndpoint.createProject(currentSession, "prj1", "project 1 description",
                dateConverter.convert(dateFormat.parse("19.07.2019")),
                dateConverter.convert(dateFormat.parse("19.05.2020")));
        projectEndpoint.createProject(currentSession, "prj2", "project 2 koshka",
                dateConverter.convert(dateFormat.parse("30.08.2019")),
                dateConverter.convert(dateFormat.parse("07.04.2020")));
        projectEndpoint.createProject(currentSession, "prj3", "project 3 kobra",
                dateConverter.convert(dateFormat.parse("03.06.2019")),
                dateConverter.convert(dateFormat.parse("02.01.2021")));
        List<ProjectDto> projectDtos = projectEndpoint.getProjectList(currentSession);
        taskEndpoint.createTask(currentSession, "task 1_1", "mishka",
                dateConverter.convert(dateFormat.parse("17.09.2019")),
                dateConverter.convert(dateFormat.parse("02.03.2020")),
                projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 1_2", "bushka",
                dateConverter.convert(dateFormat.parse("16.09.2019")),
                dateConverter.convert(dateFormat.parse("17.04.2020")), projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 1_3", "kashka",
                dateConverter.convert(dateFormat.parse("15.09.2019")),
                dateConverter.convert(dateFormat.parse("03.04.2020")),
                projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 2_1", "mishku",
                dateConverter.convert(dateFormat.parse("17.10.2019")),
                dateConverter.convert(dateFormat.parse("19.02.2020")),
                projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 2_2", "kandela",
                dateConverter.convert(dateFormat.parse("17.09.2019")),
                dateConverter.convert(dateFormat.parse("12.03.2020")),
                projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 3_1", "viking",
                dateConverter.convert(dateFormat.parse("17.09.2019")),
                dateConverter.convert(dateFormat.parse("19.01.2020")),
                projectDtos.get(2).getId());


    }

    @Test(expected = WrongSessionException_Exception.class)
    public void notLogged() throws WrongSessionException_Exception {
        Assert.assertEquals(0, projectEndpoint.getProjectList(null).size());
    }

    @Test
    public void creationTasks() throws WrongSessionException_Exception {
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
    }

    @Test
    public void creationFail() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
        taskEndpoint.createTask(currentSession, "task 3_1", "viking",
                dateConverter.convert(dateFormat.parse("17.09.2019")),
                dateConverter.convert(dateFormat.parse("19.01.2020")),
                "7");
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
    }

    @Test
    public void taskEditStartDate() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        TaskDto taskDto = taskEndpoint.getTaskList(currentSession).get(0);
        XMLGregorianCalendar calendar = dateConverter.convert(dateFormat.parse("12.12.2022"));
        String name = "nice task";
        taskEndpoint.editTask(currentSession, taskDto.getId(), name, "", calendar, null, "", null);
        TaskDto testDto = taskEndpoint.getTask(currentSession, taskDto.getId());
        Assert.assertEquals(name, testDto.getName());
        Assert.assertEquals(taskDto.getDescription(), testDto.getDescription());
        Assert.assertEquals(calendar, testDto.getStartDate());
        Assert.assertEquals(taskDto.getFinishDate(), testDto.getFinishDate());
        Assert.assertEquals(taskDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(taskDto.getStatus(), testDto.getStatus());
        Assert.assertEquals(taskDto.getProjectId(), testDto.getProjectId());
    }

    @Test
    public void taskEditFinishDateAndDescription() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        TaskDto taskDto = taskEndpoint.getTaskList(currentSession).get(0);
        XMLGregorianCalendar calendar = dateConverter.convert(dateFormat.parse("12.12.2022"));
        final String desc = "Task important desk";
        taskEndpoint.editTask(currentSession, taskDto.getId(), "", desc, null, calendar, "", null);
        TaskDto testDto = taskEndpoint.getTask(currentSession, taskDto.getId());
        Assert.assertEquals(taskDto.getName(), testDto.getName());
        Assert.assertEquals(desc, testDto.getDescription());
        Assert.assertEquals(taskDto.getStartDate(), testDto.getStartDate());
        Assert.assertEquals(calendar, testDto.getFinishDate());
        Assert.assertEquals(taskDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(taskDto.getStatus(), testDto.getStatus());
        Assert.assertEquals(taskDto.getProjectId(), testDto.getProjectId());
    }

    @Test
    public void tasktEditStatusAndProjectId() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        TaskDto taskDto = taskEndpoint.getTaskList(currentSession).get(0);
        List<ProjectDto> projectList= projectEndpoint.getProjectList(currentSession);
        String secondProjectId = projectList.get(0).getId();
        if (secondProjectId.equals(taskDto.getProjectId())) secondProjectId=projectList.get(1).getId();
        taskEndpoint.editTask(currentSession, taskDto.getId(), "", "", null, null, secondProjectId, Status.PROCESS);
        TaskDto testDto = taskEndpoint.getTask(currentSession, taskDto.getId());
        Assert.assertEquals(taskDto.getName(), testDto.getName());
        Assert.assertEquals(taskDto.getDescription(), testDto.getDescription());
        Assert.assertEquals(taskDto.getStartDate(), testDto.getStartDate());
        Assert.assertEquals(taskDto.getFinishDate(), testDto.getFinishDate());
        Assert.assertEquals(taskDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(Status.PROCESS, testDto.getStatus());
        Assert.assertEquals(secondProjectId, testDto.getProjectId());
        projectEndpoint.removeProject(currentSession, secondProjectId);
        Assert.assertNull(taskEndpoint.getTask(currentSession, taskDto.getId()));

    }

    @Test
    public void projectRemove() throws WrongSessionException_Exception {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(1);
        List<TaskDto> taskDtoList = taskEndpoint.getTaskListByProjectId(currentSession, projectDto.getId());
        Assert.assertTrue(taskDtoList.size() > 0);
        projectEndpoint.removeProject(currentSession, projectDto.getId());
        Assert.assertNull(projectEndpoint.getProject(currentSession, projectDto.getId()));
        Assert.assertEquals(0, taskEndpoint.getTaskListByProjectId(currentSession, projectDto.getId()).size());

    }

    @Test
    public void taskRemove() throws WrongSessionException_Exception {
        TaskDto taskDto = taskEndpoint.getTaskList(currentSession).get(1);
        taskEndpoint.removeTask(currentSession, taskDto.getId());
        Assert.assertNull(taskEndpoint.getTask(currentSession, taskDto.getId()));
    }


    @After
    public void afterTest() throws WrongSessionException_Exception {
        List<TaskDto> tList = taskEndpoint.getTaskList(currentSession);
        for (TaskDto t : tList) {
            taskEndpoint.removeTask(currentSession, t.getId());
        }
        List<ProjectDto> pList = projectEndpoint.getProjectList(currentSession);
        for (ProjectDto p : pList) {
            projectEndpoint.removeProject(currentSession, p.getId());
        }
        sessionEndpoint.logout(currentSession);
        currentSession = null;
    }

    @AfterClass
    public static void end() {
        container.close();
    }
}
