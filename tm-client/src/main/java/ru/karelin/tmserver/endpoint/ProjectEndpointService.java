package ru.karelin.tmserver.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-29T16:06:53.921+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "ProjectEndpointService",
                  wsdlLocation = "http://localhost:8080/ProjectEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tmserver.karelin.ru/")
public class ProjectEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tmserver.karelin.ru/", "ProjectEndpointService");
    public final static QName ProjectEndpointPort = new QName("http://endpoint.tmserver.karelin.ru/", "ProjectEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/ProjectEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProjectEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/ProjectEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProjectEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjectEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ProjectEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public ProjectEndpoint getProjectEndpointPort() {
        return super.getPort(ProjectEndpointPort, ProjectEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public ProjectEndpoint getProjectEndpointPort(WebServiceFeature... features) {
        return super.getPort(ProjectEndpointPort, ProjectEndpoint.class, features);
    }

}
