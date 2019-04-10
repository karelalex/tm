
package ru.karelin.tmserver.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.karelin.tmserver.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WrongSessionException_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "WrongSessionException");
    private final static QName _Login_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "loginResponse");
    private final static QName _Logout_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "logout");
    private final static QName _LogoutResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "logoutResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.karelin.tmserver.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WrongSessionException }
     * 
     */
    public WrongSessionException createWrongSessionException() {
        return new WrongSessionException();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link SessionDto }
     * 
     */
    public SessionDto createSessionDto() {
        return new SessionDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WrongSessionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "WrongSessionException")
    public JAXBElement<WrongSessionException> createWrongSessionException(WrongSessionException value) {
        return new JAXBElement<WrongSessionException>(_WrongSessionException_QNAME, WrongSessionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

}