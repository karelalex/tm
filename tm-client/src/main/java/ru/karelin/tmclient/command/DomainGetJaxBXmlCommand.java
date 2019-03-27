package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainGetJaxBXmlCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public DomainGetJaxBXmlCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainGetJaxBXmlCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "gjaxx";
    }

    @Override
    public String getDescription() {
        return "restores domain using jax-b and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainService domainService = locator.getDomainService();
        domainService.getJaxXML();
    }
}
