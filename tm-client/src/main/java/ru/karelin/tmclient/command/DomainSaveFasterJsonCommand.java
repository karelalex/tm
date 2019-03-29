package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;


public class DomainSaveFasterJsonCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveFasterJsonCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveFasterJsonCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sfasj";
    }

    @Override
    public String getDescription() {
        return "saves domain using FasterXLM and JSON format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
        domainEndpoint.saveDomainToJsonByFasterXml();
    }
}
