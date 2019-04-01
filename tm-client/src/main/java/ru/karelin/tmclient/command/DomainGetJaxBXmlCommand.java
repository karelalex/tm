package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;

public class DomainGetJaxBXmlCommand extends AbstractCommand {
    private static final boolean SECURED = true;
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
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.restoreDomainFromXmlByJaxB(locator.getCurrentSession());
    }
}
