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
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.config.MainConfig;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {MainConfig.class, TestConfig.class})
public class TaskServiceSortingTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    TaskService taskService;

    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User user;
    private String globalProjectId;


    @Before
    public void endPointInit() throws ParseException {
        if (!userService.isUserExistByLogin("test")) {
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
        List<Project> list = projectService.getList(user.getId());
        globalProjectId = list.get(0).getId();
        taskService.create(user.getId(), "task 1_1", "mishka",
                dateFormat.parse("17.11.2019"),
                dateFormat.parse("02.03.2020"),
                list.get(0).getId());
        taskService.create(user.getId(), "task 1_2", "bushka",
                dateFormat.parse("16.09.2019"),
                dateFormat.parse("17.04.2020"), list.get(0).getId());
        taskService.create(user.getId(), "task 1_3", "kashka",
                dateFormat.parse("15.09.2019"),
                dateFormat.parse("03.04.2020"),
                list.get(0).getId());
        taskService.create(user.getId(), "task 2_1", "mishku",
                dateFormat.parse("17.10.2019"),
                dateFormat.parse("19.02.2020"),
                list.get(1).getId());
        taskService.create(user.getId(), "task 2_2", "kandela",
                dateFormat.parse("07.09.2019"),
                dateFormat.parse("12.03.2020"),
                list.get(1).getId());
        taskService.create(user.getId(), "task 3_1", "viking",
                dateFormat.parse("17.09.2019"),
                dateFormat.parse("19.01.2020"),
                list.get(2).getId());
        List<Task> taskList = taskService.getListByProjectId(user.getId(), globalProjectId);
        taskService.edit(user.getId(), taskList.get(1).getId(), "", "", null, null, globalProjectId, Status.PROCESS);
        taskService.edit(user.getId(), taskList.get(2).getId(), "", "", null, null, globalProjectId, Status.READY);

    }

    @Test
    public void finishDateSortingTest() {
        List<Task> taskList = taskService.getSortedList(user.getId(), "fin", true);
        Assert.assertTrue(taskList.size() > 1);
        for (int i = 1; i < taskList.size(); i++) {
            Assert.assertTrue(taskList.get(i).getFinishDate().compareTo(taskList.get(i - 1).getFinishDate()) > 0);
        }
        taskList = taskService.getSortedList(user.getId(), "fin", false);
        Assert.assertTrue(taskList.size() > 1);
        for (int i = 1; i < taskList.size(); i++) {
            Assert.assertTrue(taskList.get(i).getFinishDate().compareTo(taskList.get(i - 1).getFinishDate()) < 0);
        }
        taskList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "fin", true);
        Assert.assertTrue(taskList.size() > 1);
        for (int i = 1; i < taskList.size(); i++) {
            Assert.assertTrue(taskList.get(i).getFinishDate().compareTo(taskList.get(i - 1).getFinishDate()) > 0);
        }
        taskList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "fin", false);
        Assert.assertTrue(taskList.size() > 1);
        for (int i = 1; i < taskList.size(); i++) {
            Assert.assertTrue(taskList.get(i).getFinishDate().compareTo(taskList.get(i - 1).getFinishDate()) < 0);
        }
    }

    @Test
    public void startDateSortingTest() {
        List<Task> taskDtoList = taskService.getSortedList(user.getId(), "start", true);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compareTo(taskDtoList.get(i - 1).getStartDate()) > 0);
        }
        taskDtoList = taskService.getSortedList(user.getId(), "start", false);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compareTo(taskDtoList.get(i - 1).getStartDate()) < 0);
        }
        taskDtoList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "start", true);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compareTo(taskDtoList.get(i - 1).getStartDate()) > 0);
        }
        taskDtoList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "start", false);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStartDate().compareTo(taskDtoList.get(i - 1).getStartDate()) < 0);
        }
    }

    @Test
    public void statusSortingTest() {
        List<Task> taskDtoList = taskService.getSortedList(user.getId(), "stat", true);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i - 1).getStatus().name()) >= 0);
        }
        taskDtoList = taskService.getSortedList(user.getId(), "stat", false);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i - 1).getStatus().name()) <= 0);
        }
        taskDtoList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "stat", true);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i - 1).getStatus().name()) > 0);
        }
        taskDtoList = taskService.getSortedListByProjectId(user.getId(), globalProjectId, "stat", false);
        Assert.assertTrue(taskDtoList.size() > 1);
        for (int i = 1; i < taskDtoList.size(); i++) {
            Assert.assertTrue(taskDtoList.get(i).getStatus().name().compareTo(taskDtoList.get(i - 1).getStatus().name()) < 0);
        }
    }


    @After
    public void afterTest() {
        List<Task> tList = taskService.getList(user.getId());
        for (Task t : tList) {
            taskService.remove(user.getId(), t.getId());
        }
        List<Project> pList = projectService.getList(user.getId());
        for (Project p : pList) {
            projectService.remove(user.getId(), p.getId());
        }
    }
}