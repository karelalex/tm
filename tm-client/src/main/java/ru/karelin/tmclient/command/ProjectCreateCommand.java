package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@Component
public class ProjectCreateCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

    public ProjectCreateCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "cp";
    }

    @Override
    public String getDescription() {
        return "starts create new project dialog";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException, WrongSessionException_Exception {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();

        System.out.println("Enter project name");
        @NotNull final String projectName = ts.readLn();
        System.out.println("Enter project description");
        final String projectDescription = ts.readLn();
        @NotNull String date;
        @NotNull Date projectStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = ts.readLn();
            if (date.isEmpty()) {
                projectStartDate = new Date();
                break;
            } else {
                try {
                    projectStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @NotNull Date projectFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = ts.readLn();
            try {
                projectFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        projectEndpoint.createProject(locator.getCurrentSession(), projectName, projectDescription, dateConverter.convert(projectStartDate), dateConverter.convert(projectFinishDate));
    }
}
