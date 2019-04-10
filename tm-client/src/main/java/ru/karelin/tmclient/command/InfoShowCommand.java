package ru.karelin.tmclient.command;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;


public final class InfoShowCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public InfoShowCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public InfoShowCommand() {
        super(SECURED);
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
        System.out.println("Program name: " + Manifests.read("Implementation-Title"));
        System.out.println("Version: " + Manifests.read("Implementation-Version"));
        System.out.println("Build number: " + Manifests.read("Implementation-Build"));
        System.out.println("Developer: " + Manifests.read("developer"));
    }
}
