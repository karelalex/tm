
package ru.karelin.tmserver.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-29T16:06:54.283+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "JAXBException", targetNamespace = "http://endpoint.tmserver.karelin.ru/")
public class JAXBException_Exception extends Exception {

    private ru.karelin.tmserver.endpoint.JAXBException jaxbException;

    public JAXBException_Exception() {
        super();
    }

    public JAXBException_Exception(String message) {
        super(message);
    }

    public JAXBException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public JAXBException_Exception(String message, ru.karelin.tmserver.endpoint.JAXBException jaxbException) {
        super(message);
        this.jaxbException = jaxbException;
    }

    public JAXBException_Exception(String message, ru.karelin.tmserver.endpoint.JAXBException jaxbException, java.lang.Throwable cause) {
        super(message, cause);
        this.jaxbException = jaxbException;
    }

    public ru.karelin.tmserver.endpoint.JAXBException getFaultInfo() {
        return this.jaxbException;
    }
}
