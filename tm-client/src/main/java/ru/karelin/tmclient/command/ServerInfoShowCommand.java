package ru.karelin.tmclient.command;

import ru.karelin.tmserver.endpoint.SessionEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServerInfoShowCommand extends AbstractCommand {

    @Inject
    SessionEndpoint sessionEndpoint;

    private static final boolean SECURED = false;

    public ServerInfoShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sinfo";
    }

    @Override
    public String getDescription() {
        return "shows information about server connected to";
    }

    @Override
    public void execute(String... params) {
        System.out.println(sessionEndpoint.serverInfo());
    }
}
