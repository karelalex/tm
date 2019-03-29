package ru.karelin.tmserver.endpoint;

import ru.karelin.tmserver.api.service.DomainService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public class DomainEndpoint {

    private DomainService domainService;

    public DomainEndpoint(DomainService domainService) {
        this.domainService = domainService;
    }

    @WebMethod (operationName = "saveDomainByJavaSerialization")
    public void saveSerialize() throws IOException {
        domainService.saveSerialize();
    }

    @WebMethod (operationName = "restoreDomainFromJavaSerializedFile")
    public void getSerialize() throws IOException, ClassNotFoundException {
        domainService.getSerialize();
    }

    @WebMethod(operationName = "saveDomainToXmlByJaxB")
    public void saveJaxXML() throws JAXBException {
        domainService.saveJaxXML();
    }

    @WebMethod(operationName = "restoreDomainFromXmlByJaxB")
    public void getJaxXML() throws JAXBException {
        domainService.getJaxXML();
    }

    @WebMethod(operationName = "saveDomainToJsonByJaxB")
    public void saveJaxJSON() throws JAXBException {
        domainService.saveJaxJSON();
    }

    @WebMethod(operationName = "restoreDomainFromJsonByJaxB")
    public void getJaxJSON() throws JAXBException {
        domainService.getJaxJSON();
    }

    @WebMethod(operationName = "saveDomainToXmlByFasterXml")
    public void saveFasterXML() throws IOException {
        domainService.saveFasterXML();
    }

    @WebMethod(operationName = "restoreDomainFromXmlByFasterXml")
    public void getFasterXML() throws IOException {
        domainService.getFasterXML();
    }

    @WebMethod(operationName = "saveDomainToJsonByFasterXml")
    public void saveFasterJSON() throws IOException {
        domainService.saveFasterJSON();
    }

    @WebMethod(operationName = "restoreDomainFromJsonByFasterXml")
    public void getFasterJSON() throws IOException {
        domainService.getFasterJSON();
    }
}
