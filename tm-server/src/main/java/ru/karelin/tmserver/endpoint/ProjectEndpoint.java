package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.enumeration.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public class ProjectEndpoint {

    @NotNull final private ProjectService projectService;

    public ProjectEndpoint(@NotNull final ProjectService projectService) {
        this.projectService = projectService;
    }

    @WebMethod
    public List <Project> getProjectList (@WebParam(name="userId") final String userId){
        return projectService.getList(userId);
    }

    @WebMethod
    public void removeProject (@WebParam(name="userId") final String userId,
                               @WebParam(name = "projectId") final String id){
        projectService.remove(userId, id);
    }

    @WebMethod
    public boolean checkProjectId(@WebParam(name="userId") final String userId,
                                  @WebParam(name = "projectId") final String id){
        return projectService.checkId(userId, id);
    }

    @WebMethod
    public Project getProject(@WebParam(name="userId") final String userId,
                              @WebParam(name = "projectId") final String id){
        return projectService.getOne(userId, id);
    }

    @WebMethod
    public void createProject (@WebParam(name="userId") final String userId,
                               @WebParam(name = "projectName") final String name,
                               @WebParam(name = "projectDescription") final String description,
                               @WebParam(name = "projectStartDate") final Date startDate,
                               @WebParam (name = "projectFinishDate") final Date finishDate){
        projectService.create(userId, name, description, startDate, finishDate);
    }

    @WebMethod
    public void editProject (@WebParam(name="userId") final String userId,
                      @WebParam(name = "projectId") final String id,
                      @WebParam(name = "projectName") final String name,
                      @WebParam(name = "projectDescription") final String description,
                      @WebParam(name = "projectStartDate") final Date startDate,
                      @WebParam (name = "projectFinishDate") final Date finishDate,
                      @WebParam(name="projectStatus") final Status status){
        projectService.edit(userId, id, name, description,startDate, finishDate, status);
    }

    @WebMethod
    public List<Project> getProjectSortedList(@WebParam(name="userId") final String userId,
                                       @WebParam(name = "sortField") final String sortField,
                                       @WebParam(name = "isOrderStraight") final boolean isStraight){
        return projectService.getSortedList(userId, sortField,isStraight);
    }

    @WebMethod
    public List<Project> getProjectListByKeyword(@WebParam(name = "userId") final String userId,
                                          @WebParam(name = "searchString") String keyword){
        return projectService.getListByKeyword(userId, keyword);
    }


}
