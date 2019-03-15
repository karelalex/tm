package ru.karelin.tm;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;

import java.util.HashMap;
import java.util.Map;

public class Statics {
    public static final String CREATE_PROJECT = "cp";
    public static final String REMOVE_PROJECT = "rp";
    public static final String SHOW_PROJECT_LIST = "pl";
    public static final String SHOW_PROJECT= "sp";
    public static final String EDIT_PROJECT = "ep";
    public static final String CREATE_TASK = "ct";
    public static final String SHOW_TASK_LIST = "tl";
    public static final String SHOW_TASK = "st";
    public static final String EDIT_TASK = "et";
    public static final String REMOVE_TASK = "rt";
    public static final String QUIT = "exit";

    public static final String HELP = "help";

    public static final Map<String, Project> projects = new HashMap<String, Project>();
    public static final Map<String, Task> tasks = new HashMap<String, Task>();
}
