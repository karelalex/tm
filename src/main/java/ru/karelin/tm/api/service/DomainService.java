package ru.karelin.tm.api.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface DomainService {
    void saveSerialize() throws IOException;
    void getSerialize() throws IOException, ClassNotFoundException;

    void saveJaxXML() throws JAXBException;

    void getJaxXML() throws JAXBException;

    void saveJaxJSON() throws JAXBException;

    void getJaxJSON() throws JAXBException;
}
