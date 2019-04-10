package ru.karelin.tmserver.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.dto.ProjectDto;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebService
@NoArgsConstructor
public class ProjectEndpoint {

    @NotNull
    private ProjectService projectService;
    @NotNull
    private SessionService sessionService;

    public ProjectEndpoint(@NotNull final ProjectService projectService, @NotNull SessionService sessionService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public List<ProjectDto> getProjectList(@WebParam(name = "session") final SessionDto session) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return convert(projectService.getList(session.getUserId()));
        return Collections.emptyList();

    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final SessionDto session,
                              @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) projectService.remove(session.getUserId(), id);
    }

    @WebMethod
    public boolean checkProjectId(@WebParam(name = "session") final SessionDto session,
                                  @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return projectService.checkId(session.getUserId(), id);
        return false;
    }

    @WebMethod
    public ProjectDto getProject(@WebParam(name = "session") final SessionDto session,
                                 @WebParam(name = "projectId") final String id) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return convert(projectService.getOne(session.getUserId(), id));
        return null;
    }

    @WebMethod
    public void createProject(@WebParam(name = "session") final SessionDto session,
                              @WebParam(name = "projectName") final String name,
                              @WebParam(name = "projectDescription") final String description,
                              @WebParam(name = "projectStartDate") final Date startDate,
                              @WebParam(name = "projectFinishDate") final Date finishDate) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            projectService.create(session.getUserId(), name, description, startDate, finishDate);
        }
    }

    @WebMethod
    public void editProject(@WebParam(name = "session") final SessionDto session,
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
    public List<ProjectDto> getProjectSortedList(@WebParam(name = "session") final SessionDto session,
                                                 @WebParam(name = "sortField") final String sortField,
                                                 @WebParam(name = "isOrderStraight") final boolean isStraight) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            return convert(projectService.getSortedList(session.getUserId(), sortField, isStraight));
        }
        return Collections.emptyList();
    }

    @WebMethod
    public List<ProjectDto> getProjectListByKeyword(@WebParam(name = "session") final SessionDto session,
                                                    @WebParam(name = "searchString") String keyword) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) {
            return convert(projectService.getListByKeyword(session.getUserId(), keyword));
        }
        return Collections.emptyList();
    }

    @Contract(value = "null -> null")
    private ProjectDto convert(@Nullable final Project project) {
        if (project == null) return null;
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setCreationDate(project.getCreationDate());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setFinishDate(project.getFinishDate());
        projectDto.setStatus(project.getStatus());
        return projectDto;
    }

    private List<ProjectDto> convert(List<Project> projects) {
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project p : projects) {
            projectDtos.add(convert(p));
        }
        return projectDtos;
    }
}


