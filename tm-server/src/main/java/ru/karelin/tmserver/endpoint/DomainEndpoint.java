package ru.karelin.tmserver.endpoint;

import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.PermissionException;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public class DomainEndpoint {

    private DomainService domainService;
    private SessionService sessionServiceImpl;

    public DomainEndpoint(DomainService domainService, SessionService sessionServiceImpl) {
        this.domainService = domainService;
        this.sessionServiceImpl = sessionServiceImpl;
    }

    @WebMethod (operationName = "saveDomainByJavaSerialization")
    public void saveSerialize(SessionDto session) throws IOException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.saveSerialize(session.getUserId());
    }

    @WebMethod (operationName = "restoreDomainFromJavaSerializedFile")
    public void getSerialize(SessionDto session) throws IOException, ClassNotFoundException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.getSerialize(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToXmlByJaxB")
    public void saveJaxXML(SessionDto session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.saveJaxXML(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromXmlByJaxB")
    public void getJaxXML(SessionDto session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.getJaxXML(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToJsonByJaxB")
    public void saveJaxJSON(SessionDto session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.saveJaxJSON(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromJsonByJaxB")
    public void getJaxJSON(SessionDto session) throws JAXBException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.getJaxJSON(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToXmlByFasterXml")
    public void saveFasterXML(SessionDto session) throws IOException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.saveFasterXML(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromXmlByFasterXml")
    public void getFasterXML(SessionDto session) throws IOException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.getFasterXML(session.getUserId());
    }

    @WebMethod(operationName = "saveDomainToJsonByFasterXml")
    public void saveFasterJSON(SessionDto session) throws IOException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.saveFasterJSON(session.getUserId());
    }

    @WebMethod(operationName = "restoreDomainFromJsonByFasterXml")
    public void getFasterJSON(SessionDto session) throws IOException, PermissionException, WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) domainService.getFasterJSON(session.getUserId());
    }
}
