package ru.karelin.tmclient;

import org.junit.*;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;


public class CrudTesting {
    private static ProjectEndpoint projectEndpoint;
    private static SessionEndpoint sessionEndpoint;
    private static DateConverter dateConverter;
    private static UserEndpoint userEndpoint;
    private static SessionDto currentSession = null;
    private static TaskEndpoint taskEndpoint;
    private static SeContainer container;
    private static DatatypeFactory factory;




    @BeforeClass
    public static void MainInit() throws DatatypeConfigurationException {
        container = SeContainerInitializer.newInstance().initialize();
        userEndpoint= CDI.current().select(UserEndpoint.class).get();
        sessionEndpoint = CDI.current().select(SessionEndpoint.class).get();
        projectEndpoint = CDI.current().select(ProjectEndpoint.class).get();
        taskEndpoint = CDI.current().select(TaskEndpoint.class).get();
        dateConverter = CDI.current().select(DateConverter.class).get();
        if (!userEndpoint.isUserExistsByLogin("testUser")){
            userEndpoint.registerNewUser("testUser", "testPass", "Test User");
        }
        factory = DatatypeFactory.newInstance();

    }

    @Before
    public void endPointInit() throws WrongSessionException_Exception, DatatypeConfigurationException {
        currentSession = sessionEndpoint.login("testUser", "testPass");
        projectEndpoint.createProject(currentSession, "prj1", "project 1 description",
                factory.newXMLGregorianCalendarDate(2019, 7, 19, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 5,19, DatatypeConstants.FIELD_UNDEFINED));
        projectEndpoint.createProject(currentSession, "prj2", "project 2 koshka",
                factory.newXMLGregorianCalendarDate(2019, 8, 30, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 4,7, DatatypeConstants.FIELD_UNDEFINED));
        projectEndpoint.createProject(currentSession, "prj3", "project 3 kobra",
                factory.newXMLGregorianCalendarDate(2019, 6, 3, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2021, 1,2, DatatypeConstants.FIELD_UNDEFINED));
        List<ProjectDto> projectDtos = projectEndpoint.getProjectList(currentSession);
        taskEndpoint.createTask(currentSession, "task 1_1", "mishka",
                factory.newXMLGregorianCalendarDate(2019, 9, 17, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 3, 2, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 1_2", "bushka",
                factory.newXMLGregorianCalendarDate(2019, 9, 16, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 4, 17, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 1_3", "kashka",
                factory.newXMLGregorianCalendarDate(2019, 9, 15, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 4, 3, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(0).getId());
        taskEndpoint.createTask(currentSession, "task 2_1", "mishku",
                factory.newXMLGregorianCalendarDate(2019, 10, 17, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 2, 19, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 2_2", "kandela",
                factory.newXMLGregorianCalendarDate(2019, 9, 17, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2019, 12, 3, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(1).getId());
        taskEndpoint.createTask(currentSession, "task 3_1", "viking",
                factory.newXMLGregorianCalendarDate(2019, 9, 17, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 1, 19, DatatypeConstants.FIELD_UNDEFINED), projectDtos.get(2).getId());


    }

    @Test(expected = WrongSessionException_Exception.class)
    public void notLogged() throws WrongSessionException_Exception{
        Assert.assertEquals(0, projectEndpoint.getProjectList(null).size());
    }

    @Test
    public void creationProject() throws WrongSessionException_Exception {
        Assert.assertEquals(3, projectEndpoint.getProjectList(currentSession).size());
    }

    @Test
    public void creationTasks() throws WrongSessionException_Exception {
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
    }

    @Test
    public void creationFail() throws WrongSessionException_Exception {
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
        taskEndpoint.createTask(currentSession, "task 3_1", "viking",
                factory.newXMLGregorianCalendarDate(2019, 9, 17, DatatypeConstants.FIELD_UNDEFINED),
                factory.newXMLGregorianCalendarDate(2020, 1, 19, DatatypeConstants.FIELD_UNDEFINED), "7");
        Assert.assertEquals(6, taskEndpoint.getTaskList(currentSession).size());
    }

    @Test
    public void projectEditStratDate() throws WrongSessionException_Exception {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(0);
        XMLGregorianCalendar calendar = factory.newXMLGregorianCalendarDate(2022, 12, 12, DatatypeConstants.FIELD_UNDEFINED);
        projectEndpoint.editProject(currentSession, projectDto.getId(), "", "", calendar, null, null);
        //ProjectDto test
    }

    @After
    public void afterTest() throws WrongSessionException_Exception {
        List<TaskDto> tList = taskEndpoint.getTaskList(currentSession);
        for(TaskDto t: tList){
            taskEndpoint.removeTask(currentSession, t.getId());
        }
        List<ProjectDto> pList = projectEndpoint.getProjectList(currentSession);
        for(ProjectDto p: pList){
            projectEndpoint.removeProject(currentSession, p.getId());
        }
        sessionEndpoint.logout(currentSession);
        currentSession=null;
    }

    @AfterClass
    public static void end() throws WrongSessionException_Exception {
        container.close();
    }
}
