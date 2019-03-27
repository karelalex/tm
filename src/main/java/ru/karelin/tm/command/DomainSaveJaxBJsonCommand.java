package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainSaveJaxBJsonCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveJaxBJsonCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveJaxBJsonCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sjaxj";
    }

    @Override
    public String getDescription() {
        return "saves domain using jax-b and JSON format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainService domainService = locator.getDomainService();
        domainService.saveJaxJSON();

    }
}
