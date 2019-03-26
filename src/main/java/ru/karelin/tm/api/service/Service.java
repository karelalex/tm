package ru.karelin.tm.api.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Service {
    void saveSerialize() throws IOException;
    void getSerialize() throws IOException, ClassNotFoundException;

    void saveJaxXML() throws JAXBException;

    void getJaxXML() throws JAXBException;
}
