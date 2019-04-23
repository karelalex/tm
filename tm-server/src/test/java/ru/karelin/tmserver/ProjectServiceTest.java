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
import ru.karelin.tmserver.enumeration.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {MainConfig.class, TestConfig.class})
public class ProjectServiceTest {

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
    public void creationProject() {
        Assert.assertEquals(3, projectService.getList(user.getId()).size());
    }


   @Test
    public void projectRemove(){
        Project project = projectService.getList(user.getId()).get(1);
        projectService.remove(user.getId(), project.getId());
        Assert.assertNull(projectService.getOne(user.getId(), project.getId()));

    }


    @Test
    public void projectEditStartDate() throws ParseException  {
        Project project = projectService.getList(user.getId()).get(0);
        Date calendar = dateFormat.parse("12.12.2022");
        String name = "Projection1";
        projectService.edit(user.getId(), project.getId(), name, "", calendar, null, null);
        Project project1 = projectService.getOne(user.getId(), project.getId());
        Assert.assertEquals(name, project1.getName());
        Assert.assertEquals(project.getDescription(), project1.getDescription());
        Assert.assertEquals(calendar, project1.getStartDate());
        Assert.assertEquals(project.getFinishDate(), project1.getFinishDate());
        Assert.assertEquals(project.getCreationDate(), project1.getCreationDate());
        Assert.assertEquals(project.getStatus(), project1.getStatus());
    }

    @Test
    public void projectEditFinishDate() throws  ParseException {
        Project project = projectService.getList(user.getId()).get(0);
        Date calendar = dateFormat.parse("12.12.2022");
        final String desc = "Projection desk";
        projectService.edit(user.getId(), project.getId(), "", desc, null, calendar, null);
        Project project1 = projectService.getOne(user.getId(), project.getId());
        Assert.assertEquals(project.getName(), project1.getName());
        Assert.assertEquals(desc, project1.getDescription());
        Assert.assertEquals(project.getStartDate(), project1.getStartDate());
        Assert.assertEquals(calendar, project1.getFinishDate());
        Assert.assertEquals(project.getCreationDate(), project1.getCreationDate());
        Assert.assertEquals(project.getStatus(), project1.getStatus());
    }

    @Test
    public void projectEditStatus() throws  ParseException {
        Project project = projectService.getList(user.getId()).get(0);
        projectService.edit(user.getId(), project.getId(), "", "", null, null, Status.PROCESS);
        Project project1 = projectService.getOne(user.getId(), project.getId());
        Assert.assertEquals(project.getName(), project1.getName());
        Assert.assertEquals(project.getDescription(), project1.getDescription());
        Assert.assertEquals(project.getStartDate(), project1.getStartDate());
        Assert.assertEquals(project.getFinishDate(), project1.getFinishDate());
        Assert.assertEquals(project.getCreationDate(), project1.getCreationDate());
        Assert.assertEquals(Status.PROCESS, project1.getStatus());
    }


    @After
    public void remove(){
        List<Project> pList = projectService.getList(user.getId());
        for (Project p : pList) {
            projectService.remove(user.getId(), p.getId());
        }
    }
}
