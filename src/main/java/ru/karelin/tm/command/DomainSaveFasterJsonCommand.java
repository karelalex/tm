package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.util.ServiceLocator;

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
        DomainService domainService = locator.getDomainService();
        domainService.saveFasterJSON();
    }
}
