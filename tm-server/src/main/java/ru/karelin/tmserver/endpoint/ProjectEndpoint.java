package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebService
public class ProjectEndpoint {

    @NotNull
    final private ProjectService projectService;
    @NotNull
    private SessionService sessionService;

    public ProjectEndpoint(@NotNull final ProjectService projectService, @NotNull SessionService sessionService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public List<Project> getProjectList(@WebParam(name = "session") final Session session) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return projectService.getList(session.getUserId());
        return Collections.emptyList();

    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final Session session,
                              @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) projectService.remove(session.getUserId(), id);
    }

    @WebMethod
    public boolean checkProjectId(@WebParam(name = "session") final Session session,
                                  @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return projectService.checkId(session.getUserId(), id);
        return false;
    }

    @WebMethod
    public Project getProject(@WebParam(name = "session") final Session session,
                              @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return projectService.getOne(session.getUserId(), id);
        return null;
    }

    @WebMethod
    public void createProject(@WebParam(name = "session") final Session session,
                              @WebParam(name = "projectName") final String name,
                              @WebParam(name = "projectDescription") final String description,
                              @WebParam(name = "projectStartDate") final Date startDate,
                              @WebParam(name = "projectFinishDate") final Date finishDate) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            projectService.create(session.getUserId(), name, description, startDate, finishDate);
        }
    }

    @WebMethod
    public void editProject(@WebParam(name = "session") final Session session,
                            @WebParam(name = "projectId") final String id,
                            @WebParam(name = "projectName") final String name,
                            @WebParam(name = "projectDescription") final String description,
                            @WebParam(name = "projectStartDate") final Date startDate,
                            @WebParam(name = "projectFinishDate") final Date finishDate,
                            @WebParam(name = "projectStatus") final Status status) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            projectService.edit(session.getUserId(), id, name, description, startDate, finishDate, status);
        }
    }

    @WebMethod
    public List<Project> getProjectSortedList(@WebParam(name = "session") final Session session,
                                              @WebParam(name = "sortField") final String sortField,
                                              @WebParam(name = "isOrderStraight") final boolean isStraight) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            return projectService.getSortedList(session.getUserId(), sortField, isStraight);
        }
        return Collections.emptyList();
    }

    @WebMethod
    public List<Project> getProjectListByKeyword(@WebParam(name = "session") final Session session,
                                                 @WebParam(name = "searchString") String keyword) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            return projectService.getListByKeyword(session.getUserId(), keyword);
        }
        return Collections.emptyList();
    }


}
