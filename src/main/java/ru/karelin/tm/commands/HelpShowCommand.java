package ru.karelin.tm.commands;

import ru.karelin.tm.ServiceLocator;

import java.util.Map;

public final class HelpShowCommand extends AbstractCommand {
    public HelpShowCommand(final ServiceLocator locator) {
        super(locator, false);
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
