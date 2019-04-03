
package ru.karelin.tmserver.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-03T13:33:01.109+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "ClassNotFoundException", targetNamespace = "http://endpoint.tmserver.karelin.ru/")
public class ClassNotFoundException_Exception extends Exception {

    private ru.karelin.tmserver.endpoint.ClassNotFoundException classNotFoundException;

    public ClassNotFoundException_Exception() {
        super();
    }

    public ClassNotFoundException_Exception(String message) {
        super(message);
    }

    public ClassNotFoundException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public ClassNotFoundException_Exception(String message, ru.karelin.tmserver.endpoint.ClassNotFoundException classNotFoundException) {
        super(message);
        this.classNotFoundException = classNotFoundException;
    }

    public ClassNotFoundException_Exception(String message, ru.karelin.tmserver.endpoint.ClassNotFoundException classNotFoundException, java.lang.Throwable cause) {
        super(message, cause);
        this.classNotFoundException = classNotFoundException;
    }

    public ru.karelin.tmserver.endpoint.ClassNotFoundException getFaultInfo() {
        return this.classNotFoundException;
    }
}
