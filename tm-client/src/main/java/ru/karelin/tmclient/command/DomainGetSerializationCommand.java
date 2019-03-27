package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
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
        DomainService domainService = locator.getDomainService();
        domainService.getSerialize();
    }
}
