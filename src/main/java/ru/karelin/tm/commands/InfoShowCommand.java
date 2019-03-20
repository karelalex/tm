package ru.karelin.tm.commands;

import com.jcabi.manifests.Manifests;
import ru.karelin.tm.ServiceLocator;

public class InfoShowCommand extends AbstractCommand {

    public InfoShowCommand(final ServiceLocator locator) {
        super(locator, false);
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "shows information about this application";
    }

    @Override
    public void execute(String... params) {
        System.out.println("Build number: " + Manifests.read("Implementation-Build"));
    }
}
