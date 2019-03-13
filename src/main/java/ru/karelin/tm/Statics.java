package ru.karelin.tm;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Statics {
    public static final String CREATE_PROJECT = "create-project";
    public static final String REMOVE_PROJECT = "remove-project";
    public static final String SHOW_PROJECT_LIST = "show-project-list";
    public static final String GO_INTO_PROJECT = "enter-project";
    public static final String EDIT_PROJECT_DESCRIPTION = "edit-description";
    public static final String ADD_TASK_TO_PROJECT = "add-task";
    public static final String SHOW_PROJECT_TASK_LIST = "show-task-list";
    public static final String SHOW_PROJECT_TASK = "show-task";
    public static final String EDIT_PROJECT_TASK = "edit-task";
    public static final String REMOVE_PROJECT_TASK = "remove-task";
    public static final String QUIT = "exit";
    public static final String LEAVE_PROJECT = "exit";
    public static final String HELP = "help";
    public static final List<Project> projects = new ArrayList<Project>();
}
