package ru.karelin.tmserver.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-03T13:33:00.707+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.karelin.ru/", name = "ProjectEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/checkProjectIdRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/checkProjectIdResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/checkProjectId/Fault/WrongSessionException")})
    @RequestWrapper(localName = "checkProjectId", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.CheckProjectId")
    @ResponseWrapper(localName = "checkProjectIdResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.CheckProjectIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean checkProjectId(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/createProjectRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/createProjectResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/createProject/Fault/WrongSessionException")})
    @RequestWrapper(localName = "createProject", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.CreateProjectResponse")
    public void createProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "projectName", targetNamespace = "")
        java.lang.String projectName,
        @WebParam(name = "projectDescription", targetNamespace = "")
        java.lang.String projectDescription,
        @WebParam(name = "projectStartDate", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar projectStartDate,
        @WebParam(name = "projectFinishDate", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar projectFinishDate
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectListByKeywordRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectListByKeywordResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectListByKeyword/Fault/WrongSessionException")})
    @RequestWrapper(localName = "getProjectListByKeyword", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectListByKeyword")
    @ResponseWrapper(localName = "getProjectListByKeywordResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectListByKeywordResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.karelin.tmserver.endpoint.Project> getProjectListByKeyword(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "searchString", targetNamespace = "")
        java.lang.String searchString
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectListRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectListResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectList/Fault/WrongSessionException")})
    @RequestWrapper(localName = "getProjectList", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectList")
    @ResponseWrapper(localName = "getProjectListResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectListResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.karelin.tmserver.endpoint.Project> getProjectList(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProject/Fault/WrongSessionException")})
    @RequestWrapper(localName = "getProject", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProject")
    @ResponseWrapper(localName = "getProjectResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.karelin.tmserver.endpoint.Project getProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/editProjectRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/editProjectResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/editProject/Fault/WrongSessionException")})
    @RequestWrapper(localName = "editProject", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.EditProject")
    @ResponseWrapper(localName = "editProjectResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.EditProjectResponse")
    public void editProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId,
        @WebParam(name = "projectName", targetNamespace = "")
        java.lang.String projectName,
        @WebParam(name = "projectDescription", targetNamespace = "")
        java.lang.String projectDescription,
        @WebParam(name = "projectStartDate", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar projectStartDate,
        @WebParam(name = "projectFinishDate", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar projectFinishDate,
        @WebParam(name = "projectStatus", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Status projectStatus
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectSortedListRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectSortedListResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/getProjectSortedList/Fault/WrongSessionException")})
    @RequestWrapper(localName = "getProjectSortedList", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectSortedList")
    @ResponseWrapper(localName = "getProjectSortedListResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetProjectSortedListResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.karelin.tmserver.endpoint.Project> getProjectSortedList(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "sortField", targetNamespace = "")
        java.lang.String sortField,
        @WebParam(name = "isOrderStraight", targetNamespace = "")
        boolean isOrderStraight
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/removeProjectRequest", output = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/removeProjectResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/ProjectEndpoint/removeProject/Fault/WrongSessionException")})
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RemoveProjectResponse")
    public void removeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId
    ) throws WrongSessionException_Exception;
}
