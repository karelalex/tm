package ru.karelin.tmserver.endpoint;

import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.PermissionException;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public class DomainEndpoint {

    private DomainService domainService;
    private SessionService sessionService;

    public DomainEndpoint(DomainService domainService, SessionService sessionService) {
        this.domainService = domainService;
        this.sessionService = sessionService;
    }

    @WebMethod (operationName = "saveDomainByJavaSerialization")
    public void saveSerialize(Session session) throws IOException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.saveSerialize(session.getUserId());
    }

    @WebMethod (operationName = "restoreDomainFromJavaSerializedFile")
    public void getSerialize(Session session) throws IOException, ClassNotFoundException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.getSerialize(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToXmlByJaxB")
    public void saveJaxXML(Session session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.saveJaxXML(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromXmlByJaxB")
    public void getJaxXML(Session session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.getJaxXML(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToJsonByJaxB")
    public void saveJaxJSON(Session session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.saveJaxJSON(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromJsonByJaxB")
    public void getJaxJSON(Session session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.getJaxJSON(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToXmlByFasterXml")
    public void saveFasterXML(Session session) throws IOException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.saveFasterXML(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromXmlByFasterXml")
    public void getFasterXML(Session session) throws IOException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.getFasterXML(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToJsonByFasterXml")
    public void saveFasterJSON(Session session) throws IOException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.saveFasterJSON(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromJsonByFasterXml")
    public void getFasterJSON(Session session) throws IOException, PermissionException, WrongSessionException {
        if(sessionService.isSessionExists(session)) domainService.getFasterJSON(session.getUserId());
    }
}
