package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;

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
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.restoreDomainFromXmlByFasterXml();
    }
}
