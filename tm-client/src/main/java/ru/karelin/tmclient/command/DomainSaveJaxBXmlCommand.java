package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


public class DomainSaveJaxBXmlCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveJaxBXmlCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveJaxBXmlCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sjaxx";
    }

    @Override
    public String getDescription() {
        return "saves domain using jax-b and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.saveDomainToXmlByJaxB(locator.getCurrentSession());
    }
}
