package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;

import java.util.Map;


public final class HelpShowCommand extends AbstractCommand {
    private static final boolean SECURED = false;
    public HelpShowCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public HelpShowCommand(){
        super(SECURED);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "shows this help";
    }

    @Override
    public void execute(final String... params) {
        final Map<String, AbstractCommand> commands = locator.getCommands();
        System.out.println("Commands: ");
        for (AbstractCommand c : commands.values()
        ) {
            System.out.println("'" + c.getName() + "' " + c.getDescription());
        }
        System.out.println("'exit' closes program");
    }
}
