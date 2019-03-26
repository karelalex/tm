package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainSaveJaxBXlmlCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveJaxBXlmlCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveJaxBXlmlCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sjaxx";
    }

    @Override
    public String getDescription() {
        return "saves domain using jax-b and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
        ProjectService projectService = locator.getProjectService();
        UserService userService = locator.getUserService();
        TaskService taskService = locator.getTaskService();
        projectService.saveJaxXML();
        userService.saveJaxXML();
        taskService.saveJaxXML();
    }
}
