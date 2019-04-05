package ru.karelin.tmserver.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-05T16:40:12.027+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.karelin.ru/", name = "DomainEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface DomainEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFileRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFileResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFile/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFile/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFile/Fault/WrongSessionException"), @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJavaSerializedFile/Fault/ClassNotFoundException")})
    @RequestWrapper(localName = "restoreDomainFromJavaSerializedFile", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJavaSerializedFile")
    @ResponseWrapper(localName = "restoreDomainFromJavaSerializedFileResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJavaSerializedFileResponse")
    public void restoreDomainFromJavaSerializedFile(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception, ClassNotFoundException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByFasterXmlRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByFasterXmlResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByFasterXml/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByFasterXml/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByFasterXml/Fault/WrongSessionException")})
    @RequestWrapper(localName = "restoreDomainFromJsonByFasterXml", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJsonByFasterXml")
    @ResponseWrapper(localName = "restoreDomainFromJsonByFasterXmlResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJsonByFasterXmlResponse")
    public void restoreDomainFromJsonByFasterXml(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByJaxBRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByJaxBResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByJaxB/Fault/PermissionException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByJaxB/Fault/WrongSessionException"), @FaultAction(className = JAXBException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByJaxB/Fault/JAXBException")})
    @RequestWrapper(localName = "saveDomainToXmlByJaxB", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToXmlByJaxB")
    @ResponseWrapper(localName = "saveDomainToXmlByJaxBResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToXmlByJaxBResponse")
    public void saveDomainToXmlByJaxB(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, WrongSessionException_Exception, JAXBException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByJaxBRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByJaxBResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByJaxB/Fault/PermissionException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByJaxB/Fault/WrongSessionException"), @FaultAction(className = JAXBException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByJaxB/Fault/JAXBException")})
    @RequestWrapper(localName = "restoreDomainFromXmlByJaxB", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromXmlByJaxB")
    @ResponseWrapper(localName = "restoreDomainFromXmlByJaxBResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromXmlByJaxBResponse")
    public void restoreDomainFromXmlByJaxB(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, WrongSessionException_Exception, JAXBException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByJaxBRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByJaxBResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByJaxB/Fault/PermissionException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByJaxB/Fault/WrongSessionException"), @FaultAction(className = JAXBException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromJsonByJaxB/Fault/JAXBException")})
    @RequestWrapper(localName = "restoreDomainFromJsonByJaxB", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJsonByJaxB")
    @ResponseWrapper(localName = "restoreDomainFromJsonByJaxBResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromJsonByJaxBResponse")
    public void restoreDomainFromJsonByJaxB(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, WrongSessionException_Exception, JAXBException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByJaxBRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByJaxBResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByJaxB/Fault/PermissionException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByJaxB/Fault/WrongSessionException"), @FaultAction(className = JAXBException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByJaxB/Fault/JAXBException")})
    @RequestWrapper(localName = "saveDomainToJsonByJaxB", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToJsonByJaxB")
    @ResponseWrapper(localName = "saveDomainToJsonByJaxBResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToJsonByJaxBResponse")
    public void saveDomainToJsonByJaxB(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, WrongSessionException_Exception, JAXBException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByFasterXmlRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByFasterXmlResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByFasterXml/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByFasterXml/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/restoreDomainFromXmlByFasterXml/Fault/WrongSessionException")})
    @RequestWrapper(localName = "restoreDomainFromXmlByFasterXml", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromXmlByFasterXml")
    @ResponseWrapper(localName = "restoreDomainFromXmlByFasterXmlResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.RestoreDomainFromXmlByFasterXmlResponse")
    public void restoreDomainFromXmlByFasterXml(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByFasterXmlRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByFasterXmlResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByFasterXml/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByFasterXml/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToJsonByFasterXml/Fault/WrongSessionException")})
    @RequestWrapper(localName = "saveDomainToJsonByFasterXml", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToJsonByFasterXml")
    @ResponseWrapper(localName = "saveDomainToJsonByFasterXmlResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToJsonByFasterXmlResponse")
    public void saveDomainToJsonByFasterXml(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainByJavaSerializationRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainByJavaSerializationResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainByJavaSerialization/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainByJavaSerialization/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainByJavaSerialization/Fault/WrongSessionException")})
    @RequestWrapper(localName = "saveDomainByJavaSerialization", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainByJavaSerialization")
    @ResponseWrapper(localName = "saveDomainByJavaSerializationResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainByJavaSerializationResponse")
    public void saveDomainByJavaSerialization(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByFasterXmlRequest", output = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByFasterXmlResponse", fault = {@FaultAction(className = PermissionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByFasterXml/Fault/PermissionException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByFasterXml/Fault/IOException"), @FaultAction(className = WrongSessionException_Exception.class, value = "http://endpoint.tmserver.karelin.ru/DomainEndpoint/saveDomainToXmlByFasterXml/Fault/WrongSessionException")})
    @RequestWrapper(localName = "saveDomainToXmlByFasterXml", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToXmlByFasterXml")
    @ResponseWrapper(localName = "saveDomainToXmlByFasterXmlResponse", targetNamespace = "http://endpoint.tmserver.karelin.ru/", className = "ru.karelin.tmserver.endpoint.SaveDomainToXmlByFasterXmlResponse")
    public void saveDomainToXmlByFasterXml(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.karelin.tmserver.endpoint.Session arg0
    ) throws PermissionException_Exception, IOException_Exception, WrongSessionException_Exception;
}
