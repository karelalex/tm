package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.util.ServiceLocator;

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
        return "saves domain using FasterXLM and XML format";
    }

    @Override
    public void execute(String... params) throws Exception {
        DomainService domainService = locator.getDomainService();
        domainService.saveFasterXML();
    }
}
