package ru.karelin.tm.commands;

import ru.karelin.tm.ServiceLocator;

import java.util.Map;

public class HelpShowCommand extends AbstractCommand {
    public HelpShowCommand(ServiceLocator bootstrap) {
        super(bootstrap, false);
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
    public void execute(String... params) {
        Map<String, AbstractCommand> commands = locator.getCommands();
        System.out.println("Commands: ");
        for (AbstractCommand c : commands.values()
        ) {
            System.out.println("'" + c.getName() + "' " + c.getDescription());
        }
        System.out.println("'exit' closes program");
    }
}
