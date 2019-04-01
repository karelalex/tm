package ru.karelin.tmserver.api.service;

import ru.karelin.tmserver.exception.PermissionException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface DomainService {
    void saveSerialize(String userId) throws IOException, PermissionException;

    void getSerialize(String userId) throws IOException, ClassNotFoundException, PermissionException;

    void saveJaxXML(String userId) throws JAXBException, PermissionException;

    void getJaxXML(String userId) throws JAXBException, PermissionException;

    void saveJaxJSON(String userId) throws JAXBException, PermissionException;

    void getJaxJSON(String userId) throws JAXBException, PermissionException;

    void saveFasterXML(String userId) throws IOException, PermissionException;

    void getFasterXML(String userId) throws IOException, PermissionException;

    void saveFasterJSON(String userId) throws IOException, PermissionException;

    void getFasterJSON(String userId) throws IOException, PermissionException;
}
