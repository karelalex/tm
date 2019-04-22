package ru.karelin.tmserver;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@RunWith(CdiTestRunner.class)
public class TaskServiceTest {

    private ProjectService projectService;

    private UserService userService;

    private TaskService taskService;

    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User user;


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
        taskService.create(user.getId(), "task 1_1", "mishka",
                dateFormat.parse("17.09.2019"),
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
                dateFormat.parse("17.09.2019"),
                dateFormat.parse("12.03.2020"),
                list.get(1).getId());
        taskService.create(user.getId(), "task 3_1", "viking",
                dateFormat.parse("17.09.2019"),
                dateFormat.parse("19.01.2020"),
                list.get(2).getId());

    }

    @Test
    public void creationTasks() {
        Assert.assertEquals(6, taskService.getList(user.getId()).size());
    }

    @Test
    public void creationFail() throws ParseException {
        Assert.assertEquals(6, taskService.getList(user.getId()).size());
        taskService.create(user.getId(), "task 3_1", "viking",
                dateFormat.parse("17.09.2019"),
                dateFormat.parse("19.01.2020"),
                "7");
        Assert.assertEquals(6, taskService.getList(user.getId()).size());
    }

    @Test
    public void taskEditStartDate() throws ParseException {
        Task task = taskService.getList(user.getId()).get(0);
        Date calendar = dateFormat.parse("12.12.2022");
        String name = "nice task";
        taskService.edit(user.getId(), task.getId(), name, "", calendar, null, "", null);
        Task task1 = taskService.getOne(user.getId(), task.getId());
        Assert.assertEquals(name, task1.getName());
        Assert.assertEquals(task.getDescription(), task1.getDescription());
        Assert.assertEquals(calendar, task1.getStartDate());
        Assert.assertEquals(task.getFinishDate(), task1.getFinishDate());
        Assert.assertEquals(task.getCreationDate(), task1.getCreationDate());
        Assert.assertEquals(task.getStatus(), task1.getStatus());
        Assert.assertEquals(task.getProject().getId(), task1.getProject().getId());
    }

    @Test
    public void taskEditFinishDateAndDescription() throws ParseException {
        Task task = taskService.getList(user.getId()).get(0);
        Date calendar = dateFormat.parse("12.12.2022");
        final String desc = "Task important desk";
        taskService.edit(user.getId(), task.getId(), "", desc, null, calendar, "", null);
        Task task1 = taskService.getOne(user.getId(), task.getId());
        Assert.assertEquals(task.getName(), task1.getName());
        Assert.assertEquals(desc, task1.getDescription());
        Assert.assertEquals(task.getStartDate(), task1.getStartDate());
        Assert.assertEquals(calendar, task1.getFinishDate());
        Assert.assertEquals(task.getCreationDate(), task1.getCreationDate());
        Assert.assertEquals(task.getStatus(), task1.getStatus());
        Assert.assertEquals(task.getProject().getId(), task1.getProject().getId());
    }

    @Test
    public void tasktEditStatusAndProjectId() throws ParseException {
        Task task = taskService.getList(user.getId()).get(0);
        List<Project> projectList = projectService.getList(user.getId());
        String secondProjectId = projectList.get(0).getId();
        if (secondProjectId.equals(task.getProject().getId())) secondProjectId = projectList.get(1).getId();
        taskService.edit(user.getId(), task.getId(), "", "", null, null, secondProjectId, Status.PROCESS);
        Task task1 = taskService.getOne(user.getId(), task.getId());
        Assert.assertEquals(task.getName(), task1.getName());
        Assert.assertEquals(task.getDescription(), task1.getDescription());
        Assert.assertEquals(task.getStartDate(), task1.getStartDate());
        Assert.assertEquals(task.getFinishDate(), task1.getFinishDate());
        Assert.assertEquals(task.getCreationDate(), task1.getCreationDate());
        Assert.assertEquals(Status.PROCESS, task1.getStatus());
        Assert.assertEquals(secondProjectId, task1.getProject().getId());
        projectService.remove(user.getId(), secondProjectId);
        Assert.assertNull(taskService.getOne(user.getId(), task.getId()));

    }

    @Test
    public void projectRemove() {
        Project project = projectService.getList(user.getId()).get(1);
        List<Task> taskList = taskService.getListByProjectId(user.getId(), project.getId());
        Assert.assertTrue(taskList.size() > 0);
        projectService.remove(user.getId(), project.getId());
        Assert.assertNull(projectService.getOne(user.getId(), project.getId()));
        Assert.assertEquals(0, taskService.getListByProjectId(user.getId(), project.getId()).size());

    }

    @Test
    public void taskRemove() {
        Task task = taskService.getList(user.getId()).get(1);
        taskService.remove(user.getId(), task.getId());
        Assert.assertNull(taskService.getOne(user.getId(), task.getId()));
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