package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainGetSerializationCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public DomainGetSerializationCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainGetSerializationCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "gser";
    }

    @Override
    public String getDescription() {
        return "restores domain using java serialization";
    }

    @Override
    public void execute(String... params) throws Exception {
        ProjectService projectService = locator.getProjectService();
        UserService userService = locator.getUserService();
        projectService.getSerialize();
        userService.getSerialize();
    }
}
