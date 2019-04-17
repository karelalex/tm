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
public class ProjectCrudTesting {
    private static ProjectEndpoint projectEndpoint;
    private static SessionEndpoint sessionEndpoint;
    private static DateConverter dateConverter;
    private static UserEndpoint userEndpoint;
    private static SessionDto currentSession = null;
    private static SeContainer container;
    private static DatatypeFactory factory;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @BeforeClass
    public static void MainInit() {
        container = SeContainerInitializer.newInstance().initialize();
        userEndpoint = CDI.current().select(UserEndpoint.class).get();
        sessionEndpoint = CDI.current().select(SessionEndpoint.class).get();
        projectEndpoint = CDI.current().select(ProjectEndpoint.class).get();
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

    }

    @Test(expected = WrongSessionException_Exception.class)
    public void notLogged() throws WrongSessionException_Exception {
        Assert.assertEquals(0, projectEndpoint.getProjectList(null).size());
    }

    @Test
    public void creationProject() throws WrongSessionException_Exception {
        Assert.assertEquals(3, projectEndpoint.getProjectList(currentSession).size());
    }


    @Test
    public void projectRemove() throws WrongSessionException_Exception {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(1);
        projectEndpoint.removeProject(currentSession, projectDto.getId());
        Assert.assertNull(projectEndpoint.getProject(currentSession, projectDto.getId()));

    }


    @Test
    public void projectEditStartDate() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(0);
        XMLGregorianCalendar calendar = dateConverter.convert(dateFormat.parse("12.12.2022"));
        String name = "Projection1";
        projectEndpoint.editProject(currentSession, projectDto.getId(), name, "", calendar, null, null);
        ProjectDto testDto = projectEndpoint.getProject(currentSession, projectDto.getId());
        Assert.assertEquals(name, testDto.getName());
        Assert.assertEquals(projectDto.getDescription(), testDto.getDescription());
        Assert.assertEquals(calendar, testDto.getStartDate());
        Assert.assertEquals(projectDto.getFinishDate(), testDto.getFinishDate());
        Assert.assertEquals(projectDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(projectDto.getStatus(), testDto.getStatus());
    }

    @Test
    public void projectEditFinishDate() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(0);
        XMLGregorianCalendar calendar = dateConverter.convert(dateFormat.parse("12.12.2022"));
        final String desc = "Projection desk";
        projectEndpoint.editProject(currentSession, projectDto.getId(), "", desc, null, calendar, null);
        ProjectDto testDto = projectEndpoint.getProject(currentSession, projectDto.getId());
        Assert.assertEquals(projectDto.getName(), testDto.getName());
        Assert.assertEquals(desc, testDto.getDescription());
        Assert.assertEquals(projectDto.getStartDate(), testDto.getStartDate());
        Assert.assertEquals(calendar, testDto.getFinishDate());
        Assert.assertEquals(projectDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(projectDto.getStatus(), testDto.getStatus());
    }

    @Test
    public void projectEditStatus() throws WrongSessionException_Exception, ParseException, DatatypeConfigurationException {
        ProjectDto projectDto = projectEndpoint.getProjectList(currentSession).get(0);
        projectEndpoint.editProject(currentSession, projectDto.getId(), "", "", null, null, Status.PROCESS);
        ProjectDto testDto = projectEndpoint.getProject(currentSession, projectDto.getId());
        Assert.assertEquals(projectDto.getName(), testDto.getName());
        Assert.assertEquals(projectDto.getDescription(), testDto.getDescription());
        Assert.assertEquals(projectDto.getStartDate(), testDto.getStartDate());
        Assert.assertEquals(projectDto.getFinishDate(), testDto.getFinishDate());
        Assert.assertEquals(projectDto.getCreationDate(), testDto.getCreationDate());
        Assert.assertEquals(Status.PROCESS, testDto.getStatus());
    }


    @After
    public void afterTest() throws WrongSessionException_Exception {
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
