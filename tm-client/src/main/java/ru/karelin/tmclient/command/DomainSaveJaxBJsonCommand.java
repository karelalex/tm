package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


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
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.saveDomainToJsonByJaxB(locator.getCurrentSession());
    }
}
