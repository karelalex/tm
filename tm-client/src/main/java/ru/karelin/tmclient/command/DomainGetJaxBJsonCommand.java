package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;

public class DomainGetJaxBJsonCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainGetJaxBJsonCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainGetJaxBJsonCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "gjaxj";
    }

    @Override
    public String getDescription() {
        return "restores domain using jax-b and JSON format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.restoreDomainFromJsonByJaxB(locator.getCurrentSession());
    }
}
