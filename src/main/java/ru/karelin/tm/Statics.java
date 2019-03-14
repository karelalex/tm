package ru.karelin.tm;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statics {
    public static final String CREATE_PROJECT = "create-project";
    public static final String REMOVE_PROJECT = "remove-project";
    public static final String SHOW_PROJECT_LIST = "show-project-list";
    public static final String SHOW_PROJECT= "show-project";
    public static final String EDIT_PROJECT = "edit-project";

    public static final String CREATE_TASK = "create-task";
    public static final String SHOW_TASK_LIST = "show-task-list";
    public static final String SHOW_TASK = "show-task";
    public static final String EDIT_TASK = "edit-task";
    public static final String REMOVE_TASK = "remove-task";
    public static final String QUIT = "exit";

    public static final String HELP = "help";

    public static final Map<String, Project> projects = new HashMap<String, Project>();
    public static final Map<String, Task> tasks = new HashMap<String, Task>();
}
