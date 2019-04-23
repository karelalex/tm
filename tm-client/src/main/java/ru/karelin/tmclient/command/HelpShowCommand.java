package ru.karelin.tmclient.command;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmclient.api.util.ServiceLocator;

import java.util.Map;

@Component
public class HelpShowCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    private static final boolean SECURED = false;
    public HelpShowCommand(){
        super(SECURED);
    }

    @Override
    @NotNull
    public String getName() {
        return "help";
    }

    @Override
    @NotNull
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
