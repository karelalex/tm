package ru.karelin.tmclient.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.api.util.TerminalService;
import ru.karelin.tmclient.command.AbstractCommand;
import ru.karelin.tmclient.exception.CommandRegisteredException;
import ru.karelin.tmserver.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Inject
    private TerminalService terminalService;

    private static final String QUIT = "exit";

    @NotNull private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    @NotNull private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Nullable private SessionDto currentSession;

       @Override
    @Nullable
    public SessionDto getCurrentSession() {
        return currentSession;
    }

    @Override
    public void setCurrentSession(@Nullable SessionDto currentSession) {
        this.currentSession = currentSession;
    }

    @NotNull
    @Override
    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    @NotNull
    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }


    @Override
    public void init(Class[] commandClasses) {

        //command registration block
        for (Class commandClass : commandClasses) {
            try {
                registerCommand(commandClass);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }


        // main loop

        String command;
        String[] commandParts, params;
        while (true) {
            System.out.print(">");
            command = terminalService.readLn();
            if (command.equals(QUIT)) break;
            commandParts = command.split(" ");
            command = commandParts[0];
            params = Arrays.copyOfRange(commandParts, 1, commandParts.length);
            AbstractCommand abstractCommand = commands.get(command);
            if (abstractCommand == null) {
                System.out.println("Wrong command name");
                continue;
            }
            if (!abstractCommand.isSecured() || currentSession != null)
                try{
                    abstractCommand.execute(params);
                }

            catch (WrongSessionException_Exception e){
                System.out.println(e.getMessage());
                System.out.println("try to login again");
                setCurrentSession(null);
            }
            catch (Exception e){
                    e.printStackTrace();
            }
            else System.out.println("Login first.");
        }

        // end of main loop


    }

    private void registerCommand(@NotNull final Class commandClass) throws IllegalAccessException, InstantiationException {
        if (AbstractCommand.class.isAssignableFrom(commandClass)) {
            AbstractCommand command = (AbstractCommand) CDI.current().select(commandClass).get();
            final String commandName = command.getName();
            if (commands.containsKey(commandName))
                throw new CommandRegisteredException("Command with name " + commandName + " is already registered");
            //command.setLocator(this);
            commands.put(commandName, command);
        } else {
            System.out.println("команда не клёвая");
        }
    }
}
