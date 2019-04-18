package ru.karelin.tmclient;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//@Category(CrudIntegration.class)
public class TaskSortingTesting {
    private static ProjectEndpoint projectEndpoint;
    private static SessionEndpoint sessionEndpoint;
    private static DateConverter dateConverter;
    private static UserEndpoint userEndpoint;
    private static SessionDto currentSession = null;
    private static TaskEndpoint taskEndpoint;
    private static SeContainer container;
    private static DatatypeFactory factory;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static String globalProjectId;


    @BeforeClass
    public static void MainInit() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        container = SeContainerInitializer.newInstance().initialize();
        userEndpoint = CDI.current().select(UserEndpoint.class).get();
        sessionEndpoint = CDI.current().select(SessionEndpoint.class).get();
        projectEndpoint = CDI.current().select(ProjectEndpoint.class).get();
        taskEndpoint = CDI.current().select(TaskEndpoint.class).get();
        dateConverter = CDI.current().select(DateConverter.class).get();
        if (!userEndpoint.isUserExistsByLogin("testUser")) {
            userEndpoint.registerNewUser("testUser", "testPass", "Test User");
        }
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
        globalProjectId =  projectDtos.get(0).getId();
        taskEndpoint.createTask(currentSession, "task 1_1", "mishka",
                dateConverter.convert(dateFormat.parse("16.09.2019")),
                dateConverter.convert(dateFormat.parse("02.03.2020")),
               globalProjectId);
        taskEndpoint.createTask(currentSession, "task 1_2", "bushka",
                dateConverter.convert(dateFormat.parse("16.10.2019")),
                dateConverter.convert(dateFormat.parse("17.04.2020")),
                globalProjectId);
        taskEndpoint.createTask(currentSession, "task 1_3", "kashka",
                dateConverter.convert(dateFormat.parse("15.09.2019")),
                dateConverter.convert(dateFormat.parse("03.04.2020")),
                globalProjectId);
        taskEndpoint.createTask(currentSession, "task 2_1", "mishku",
                dateConverter.convert(dateFormat.parse("17.10.2019")),
                dateConverter.convert(dateFormat.parse("19.02.2020")),
                projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 2_2", "kandela",
                dateConverter.convert(dateFormat.parse("17.09.2019")),
                dateConverter.convert(dateFormat.parse("12.03.2020")),
                projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 3_1", "viking",
                dateConverter.convert(dateFormat.parse("18.09.2019")),
                dateConverter.convert(dateFormat.parse("19.01.2020")),
                projectDtos.get(2).getId());
        List<TaskDto> taskDtoList = taskEndpoint.getTaskListByProjectId(currentSession, projectDtos.get(0).getId());
        taskEndpoint.editTask(currentSession, taskDtoList.get(1).getId(), "", "", null, null, globalProjectId, Status.PROCESS);
        taskEndpoint.editTask(currentSession, taskDtoList.get(2).getId(), "", "", null, null, globalProjectId, Status.READY);
    }

    @Test
    public void finishDateSortingTest() throws WrongSessionException_Exception {
        List<TaskDto> taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "fin", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getFinishDate().compare(taskDtoList.get(i-1).getFinishDate())>0);
        }
        taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "fin", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getFinishDate().compare(taskDtoList.get(i-1).getFinishDate())<0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"fin", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getFinishDate().compare(taskDtoList.get(i-1).getFinishDate())>0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"fin", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getFinishDate().compare(taskDtoList.get(i-1).getFinishDate())<0);
        }
    }

    @Test
    public void startDateSortingTest() throws WrongSessionException_Exception {
        List<TaskDto> taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "start", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compare(taskDtoList.get(i-1).getStartDate())>0);
        }
        taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "start", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compare(taskDtoList.get(i-1).getStartDate())<0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"start", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compare(taskDtoList.get(i-1).getStartDate())>0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"start", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compare(taskDtoList.get(i-1).getStartDate())<0);
        }
    }

    @Test
    public void statusSortingTest() throws WrongSessionException_Exception {
        List<TaskDto> taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "stat", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i-1).getStatus().name())>=0);
        }
        taskDtoList = taskEndpoint.getSortedTaskList(currentSession, "stat", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i-1).getStatus().name())<=0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"stat", true);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i-1).getStatus().name())>0);
        }
        taskDtoList = taskEndpoint.getSortedTaskListByProjectId(currentSession, globalProjectId,"stat", false);
        Assert.assertTrue(taskDtoList.size()>1);
        for (int i = 1; i<taskDtoList.size(); i++){
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i-1).getStatus().name())<0);
        }
    }




    @AfterClass
    public static void end() throws WrongSessionException_Exception {
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
        container.close();
    }
}
