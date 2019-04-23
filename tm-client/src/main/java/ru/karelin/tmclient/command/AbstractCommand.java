package ru.karelin.tmclient.command;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.karelin.tmclient.api.util.TerminalService;


@NoArgsConstructor
public abstract class AbstractCommand {


    @Autowired
    protected TerminalService ts;

    private boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(boolean isSecured) {
        this.isSecured=isSecured;
    }


    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(final String ... params) throws Exception;
}
