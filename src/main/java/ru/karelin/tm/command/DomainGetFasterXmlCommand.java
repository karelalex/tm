package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.util.ServiceLocator;

public class DomainGetFasterXmlCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public DomainGetFasterXmlCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainGetFasterXmlCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "gfasx";
    }

    @Override
    public String getDescription() {
        return "restores domain using fasterXML and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainService domainService = locator.getDomainService();
        domainService.getFasterXML();
    }
}
