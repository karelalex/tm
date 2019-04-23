package ru.karelin.tmclient;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karelin.tmclient.config.MainConfig;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Category(CrudIntegration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MainConfig.class})
public class ProjectSortingTesting {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private SessionEndpoint sessionEndpoint;

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private UserEndpoint userEndpoint;

    private SessionDto currentSession = null;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Before
    public void MainInit() throws ParseException, DatatypeConfigurationException, WrongSessionException_Exception {
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
        List<ProjectDto> projectDtoList = projectEndpoint.getProjectList(currentSession);
        projectEndpoint.editProject(currentSession, projectDtoList.get(1).getId(), "", "", null, null, Status.PROCESS);
        projectEndpoint.editProject(currentSession, projectDtoList.get(2).getId(), "", "", null, null, Status.READY);
    }

    @Test
    public void finishDateSortTest() throws WrongSessionException_Exception {
        List<ProjectDto> projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "fin", true);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getFinishDate().compare(projectDtoList.get(i - 1).getFinishDate()) >= 0);
        }
        projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "fin", false);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getFinishDate().compare(projectDtoList.get(i - 1).getFinishDate()) <= 0);
        }
    }

    @Test
    public void startDateSortTest() throws WrongSessionException_Exception {
        List<ProjectDto> projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "start", true);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getStartDate().compare(projectDtoList.get(i - 1).getStartDate()) >= 0);
        }
        projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "start", false);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getStartDate().compare(projectDtoList.get(i - 1).getStartDate()) <= 0);
        }
    }

    @Test
    public void statusSortTest() throws WrongSessionException_Exception {
        List<ProjectDto> projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "stat", true);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getStatus().name().compareTo(projectDtoList.get(i - 1).getStatus().name()) >= 0);
        }
        projectDtoList = projectEndpoint.getProjectSortedList(currentSession, "stat", false);
        Assert.assertTrue(projectDtoList.size() > 1);
        for (int i = 1; i < projectDtoList.size(); i++) {
            Assert.assertTrue(projectDtoList.get(i).getStatus().name().compareTo(projectDtoList.get(i - 1).getStatus().name()) <= 0);
        }
    }

    @Test
    public void searchTest() throws WrongSessionException_Exception {
        List<ProjectDto> projectDtoList = projectEndpoint.getProjectListByKeyword(currentSession, "ko");
        Assert.assertEquals(2, projectDtoList.size());
        projectDtoList = projectEndpoint.getProjectListByKeyword(currentSession, "rj");
        Assert.assertEquals(3, projectDtoList.size());

    }

    @After
    public void end() throws WrongSessionException_Exception {
        currentSession = sessionEndpoint.login("testUser", "testPass");
        List<ProjectDto> pList = projectEndpoint.getProjectList(currentSession);
        for (ProjectDto p : pList) {
            projectEndpoint.removeProject(currentSession, p.getId());
        }
        sessionEndpoint.logout(currentSession);
        currentSession = null;

    }
}
