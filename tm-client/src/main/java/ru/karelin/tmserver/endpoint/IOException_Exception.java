
package ru.karelin.tmserver.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-03T14:57:35.762+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "IOException", targetNamespace = "http://endpoint.tmserver.karelin.ru/")
public class IOException_Exception extends Exception {

    private ru.karelin.tmserver.endpoint.IOException ioException;

    public IOException_Exception() {
        super();
    }

    public IOException_Exception(String message) {
        super(message);
    }

    public IOException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public IOException_Exception(String message, ru.karelin.tmserver.endpoint.IOException ioException) {
        super(message);
        this.ioException = ioException;
    }

    public IOException_Exception(String message, ru.karelin.tmserver.endpoint.IOException ioException, java.lang.Throwable cause) {
        super(message, cause);
        this.ioException = ioException;
    }

    public ru.karelin.tmserver.endpoint.IOException getFaultInfo() {
        return this.ioException;
    }
}
