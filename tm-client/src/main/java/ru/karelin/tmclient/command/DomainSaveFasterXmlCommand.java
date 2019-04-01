package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.DomainEndpoint;

public class DomainSaveFasterXmlCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public DomainSaveFasterXmlCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        super(locator, SECURED);
    }

    public DomainSaveFasterXmlCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sfasx";
    }

    @Override
    public String getDescription() {
        return "saves domain using FasterXML and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
       DomainEndpoint domainEndpoint = locator.getDomainEndpoint();
       domainEndpoint.saveDomainToXmlByFasterXml(locator.getCurrentSession());

    }
}
