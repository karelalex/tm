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
 * 2019-04-10T11:39:10.938+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.karelin.ru/", name = "UserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/UserEndpoint/isUserExistsByLoginRequest", output = "http://endpoint.tmserver.karelin.ru/UserEndpoint/isUserExistsByLoginResponse")
    @RequestWrapper(localName = "isUserExistsByLogin", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.IsUserExistsByLogin")
    @ResponseWrapper(localName = "isUserExistsByLoginResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.IsUserExistsByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean isUserExistsByLogin(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/UserEndpoint/editUserRequest", output = "http://endpoint.tmserver.karelin.ru/UserEndpoint/editUserResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/UserEndpoint/editUser/Fault/WrongSessionException")})
    @RequestWrapper(localName = "editUser", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.EditUser")
    @ResponseWrapper(localName = "editUserResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.EditUserResponse")
    public void editUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.SessionDto session,
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/UserEndpoint/registerNewUserRequest", output = "http://endpoint.tmserver.karelin.ru/UserEndpoint/registerNewUserResponse")
    @RequestWrapper(localName = "registerNewUser", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RegisterNewUser")
    @ResponseWrapper(localName = "registerNewUserResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RegisterNewUserResponse")
    public void registerNewUser(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "userName", targetNamespace = "")
        java.lang.String userName
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/UserEndpoint/getCurrentUserRequest", output = "http://endpoint.tmserver.karelin.ru/UserEndpoint/getCurrentUserResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/UserEndpoint/getCurrentUser/Fault/WrongSessionException")})
    @RequestWrapper(localName = "getCurrentUser", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetCurrentUser")
    @ResponseWrapper(localName = "getCurrentUserResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.GetCurrentUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.karelin.tmserver.endpoint.User getCurrentUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.SessionDto session
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/UserEndpoint/changePasswordRequest", output = "http://endpoint.tmserver.karelin.ru/UserEndpoint/changePasswordResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/UserEndpoint/changePassword/Fault/WrongSessionException")})
    @RequestWrapper(localName = "changePassword", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.ChangePassword")
    @ResponseWrapper(localName = "changePasswordResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.ChangePasswordResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean changePassword(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.SessionDto session,
        @WebParam(name = "oldPassword", targetNamespace = "")
        java.lang.String oldPassword,
        @WebParam(name = "newPassword", targetNamespace = "")
        java.lang.String newPassword
    ) throws WrongSessionException_Exception;
}
