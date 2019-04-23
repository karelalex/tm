package ru.karelin.tmserver;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.config.MainConfig;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {MainConfig.class, TestConfig.class})
public class ProjectServiceSortingTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private  UserService userService;

    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User user;

    @Before
    public void endPointInit() throws ParseException {
        if(!userService.isUserExistByLogin("test"))
        {
            userService.registerNewUser("test", "test".toCharArray(), "test user");
        }
        user = userService.getUserByLoginAndPassword("test", "test".toCharArray());
        projectService.create(user.getId(), "prj1", "project 1 description",
                dateFormat.parse("19.07.2019"),
                dateFormat.parse("19.05.2020"));
        projectService.create(user.getId(), "prj2", "project 2 koshka",
                dateFormat.parse("30.08.2019"),
                dateFormat.parse("07.04.2020"));
        projectService.create(user.getId(), "prj3", "project 3 kobra",
                dateFormat.parse("03.06.2019"),
                dateFormat.parse("02.01.2021"));
    }

    @Test
    public void finishDateSortTest()  {
        List<Project> projectList = projectService.getSortedList(user.getId(), "fin", true);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getFinishDate().compareTo(projectList.get(i - 1).getFinishDate()) >= 0);
        }
        projectList = projectService.getSortedList(user.getId(), "fin", false);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getFinishDate().compareTo(projectList.get(i - 1).getFinishDate()) <= 0);
        }
    }

    @Test
    public void startDateSortTest(){
        List<Project> projectList = projectService.getSortedList(user.getId(), "start", true);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getStartDate().compareTo(projectList.get(i - 1).getStartDate()) >= 0);
        }
        projectList = projectService.getSortedList(user.getId(), "start", false);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getStartDate().compareTo(projectList.get(i - 1).getStartDate()) <= 0);
        }
    }

    @Test
    public void statusSortTest()  {
        List<Project> projectList = projectService.getSortedList(user.getId(), "stat", true);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getStatus().name().compareTo(projectList.get(i - 1).getStatus().name()) >= 0);
        }
        projectList = projectService.getSortedList(user.getId(), "stat", false);
        Assert.assertTrue(projectList.size() > 1);
        for (int i = 1; i < projectList.size(); i++) {
            Assert.assertTrue(projectList.get(i).getStatus().name().compareTo(projectList.get(i - 1).getStatus().name()) <= 0);
        }
    }

    @Test
    public void searchTest()  {
        List<Project> projectDtoList = projectService.getListByKeyword(user.getId(), "ko");
        Assert.assertEquals(2, projectDtoList.size());
        projectDtoList = projectService.getListByKeyword(user.getId(), "rj");
        Assert.assertEquals(3, projectDtoList.size());

    }



    @After
    public void remove(){
        List<Project> pList = projectService.getList(user.getId());
        for (Project p : pList) {
            projectService.remove(user.getId(), p.getId());
        }
    }
}
