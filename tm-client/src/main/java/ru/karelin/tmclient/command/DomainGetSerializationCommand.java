package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


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
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.restoreDomainFromJavaSerializedFile();
    }
}
