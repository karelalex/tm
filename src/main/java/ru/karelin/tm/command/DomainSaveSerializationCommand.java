package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainSaveSerializationCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveSerializationCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveSerializationCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sser";
    }

    @Override
    public String getDescription() {
        return "saves domain using java serialization";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainService domainService = locator.getDomainService();
        domainService.saveSerialize();
    }
}
