package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


public class DomainGetFasterJsonCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public DomainGetFasterJsonCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainGetFasterJsonCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "gfasj";
    }

    @Override
    public String getDescription() {
        return "restores domain using fasterXML and JSON format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.restoreDomainFromJsonByFasterXml();

    }
}
