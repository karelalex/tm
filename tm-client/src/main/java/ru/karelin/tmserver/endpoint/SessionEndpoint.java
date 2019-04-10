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
 * 2019-04-10T11:39:13.342+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.karelin.ru/", name = "SessionEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/SessionEndpoint/logoutRequest", output = "http://endpoint.tmserver.karelin.ru/SessionEndpoint/logoutResponse", fault = {@FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/SessionEndpoint/logout/Fault/WrongSessionException")})
    @RequestWrapper(localName = "logout", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.LogoutResponse")
    public void logout(
        @WebParam(name = "session", targetNamespace = "")
        ru.karelin.tmserver.endpoint.SessionDto session
    ) throws WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/SessionEndpoint/loginRequest", output = "http://endpoint.tmserver.karelin.ru/SessionEndpoint/loginResponse")
    @RequestWrapper(localName = "login", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.LoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.karelin.tmserver.endpoint.SessionDto login(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );
}