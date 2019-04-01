package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


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
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.saveDomainByJavaSerialization(locator.getCurrentSession());
    }
}
