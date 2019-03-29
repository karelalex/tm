
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

    private final static QName _ClassNotFoundException_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "ClassNotFoundException");
    private final static QName _IOException_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "IOException");
    private final static QName _JAXBException_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "JAXBException");
    private final static QName _RestoreDomainFromJavaSerializedFile_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJavaSerializedFile");
    private final static QName _RestoreDomainFromJavaSerializedFileResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJavaSerializedFileResponse");
    private final static QName _RestoreDomainFromJsonByFasterXml_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJsonByFasterXml");
    private final static QName _RestoreDomainFromJsonByFasterXmlResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJsonByFasterXmlResponse");
    private final static QName _RestoreDomainFromJsonByJaxB_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJsonByJaxB");
    private final static QName _RestoreDomainFromJsonByJaxBResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromJsonByJaxBResponse");
    private final static QName _RestoreDomainFromXmlByFasterXml_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromXmlByFasterXml");
    private final static QName _RestoreDomainFromXmlByFasterXmlResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromXmlByFasterXmlResponse");
    private final static QName _RestoreDomainFromXmlByJaxB_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromXmlByJaxB");
    private final static QName _RestoreDomainFromXmlByJaxBResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "restoreDomainFromXmlByJaxBResponse");
    private final static QName _SaveDomainByJavaSerialization_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainByJavaSerialization");
    private final static QName _SaveDomainByJavaSerializationResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainByJavaSerializationResponse");
    private final static QName _SaveDomainToJsonByFasterXml_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToJsonByFasterXml");
    private final static QName _SaveDomainToJsonByFasterXmlResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToJsonByFasterXmlResponse");
    private final static QName _SaveDomainToJsonByJaxB_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToJsonByJaxB");
    private final static QName _SaveDomainToJsonByJaxBResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToJsonByJaxBResponse");
    private final static QName _SaveDomainToXmlByFasterXml_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToXmlByFasterXml");
    private final static QName _SaveDomainToXmlByFasterXmlResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToXmlByFasterXmlResponse");
    private final static QName _SaveDomainToXmlByJaxB_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToXmlByJaxB");
    private final static QName _SaveDomainToXmlByJaxBResponse_QNAME = new QName("http://endpoint.tmserver.karelin.ru/", "saveDomainToXmlByJaxBResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.karelin.tmserver.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClassNotFoundException }
     * 
     */
    public ClassNotFoundException createClassNotFoundException() {
        return new ClassNotFoundException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link JAXBException }
     * 
     */
    public JAXBException createJAXBException() {
        return new JAXBException();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJavaSerializedFile }
     * 
     */
    public RestoreDomainFromJavaSerializedFile createRestoreDomainFromJavaSerializedFile() {
        return new RestoreDomainFromJavaSerializedFile();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJavaSerializedFileResponse }
     * 
     */
    public RestoreDomainFromJavaSerializedFileResponse createRestoreDomainFromJavaSerializedFileResponse() {
        return new RestoreDomainFromJavaSerializedFileResponse();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJsonByFasterXml }
     * 
     */
    public RestoreDomainFromJsonByFasterXml createRestoreDomainFromJsonByFasterXml() {
        return new RestoreDomainFromJsonByFasterXml();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJsonByFasterXmlResponse }
     * 
     */
    public RestoreDomainFromJsonByFasterXmlResponse createRestoreDomainFromJsonByFasterXmlResponse() {
        return new RestoreDomainFromJsonByFasterXmlResponse();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJsonByJaxB }
     * 
     */
    public RestoreDomainFromJsonByJaxB createRestoreDomainFromJsonByJaxB() {
        return new RestoreDomainFromJsonByJaxB();
    }

    /**
     * Create an instance of {@link RestoreDomainFromJsonByJaxBResponse }
     * 
     */
    public RestoreDomainFromJsonByJaxBResponse createRestoreDomainFromJsonByJaxBResponse() {
        return new RestoreDomainFromJsonByJaxBResponse();
    }

    /**
     * Create an instance of {@link RestoreDomainFromXmlByFasterXml }
     * 
     */
    public RestoreDomainFromXmlByFasterXml createRestoreDomainFromXmlByFasterXml() {
        return new RestoreDomainFromXmlByFasterXml();
    }

    /**
     * Create an instance of {@link RestoreDomainFromXmlByFasterXmlResponse }
     * 
     */
    public RestoreDomainFromXmlByFasterXmlResponse createRestoreDomainFromXmlByFasterXmlResponse() {
        return new RestoreDomainFromXmlByFasterXmlResponse();
    }

    /**
     * Create an instance of {@link RestoreDomainFromXmlByJaxB }
     * 
     */
    public RestoreDomainFromXmlByJaxB createRestoreDomainFromXmlByJaxB() {
        return new RestoreDomainFromXmlByJaxB();
    }

    /**
     * Create an instance of {@link RestoreDomainFromXmlByJaxBResponse }
     * 
     */
    public RestoreDomainFromXmlByJaxBResponse createRestoreDomainFromXmlByJaxBResponse() {
        return new RestoreDomainFromXmlByJaxBResponse();
    }

    /**
     * Create an instance of {@link SaveDomainByJavaSerialization }
     * 
     */
    public SaveDomainByJavaSerialization createSaveDomainByJavaSerialization() {
        return new SaveDomainByJavaSerialization();
    }

    /**
     * Create an instance of {@link SaveDomainByJavaSerializationResponse }
     * 
     */
    public SaveDomainByJavaSerializationResponse createSaveDomainByJavaSerializationResponse() {
        return new SaveDomainByJavaSerializationResponse();
    }

    /**
     * Create an instance of {@link SaveDomainToJsonByFasterXml }
     * 
     */
    public SaveDomainToJsonByFasterXml createSaveDomainToJsonByFasterXml() {
        return new SaveDomainToJsonByFasterXml();
    }

    /**
     * Create an instance of {@link SaveDomainToJsonByFasterXmlResponse }
     * 
     */
    public SaveDomainToJsonByFasterXmlResponse createSaveDomainToJsonByFasterXmlResponse() {
        return new SaveDomainToJsonByFasterXmlResponse();
    }

    /**
     * Create an instance of {@link SaveDomainToJsonByJaxB }
     * 
     */
    public SaveDomainToJsonByJaxB createSaveDomainToJsonByJaxB() {
        return new SaveDomainToJsonByJaxB();
    }

    /**
     * Create an instance of {@link SaveDomainToJsonByJaxBResponse }
     * 
     */
    public SaveDomainToJsonByJaxBResponse createSaveDomainToJsonByJaxBResponse() {
        return new SaveDomainToJsonByJaxBResponse();
    }

    /**
     * Create an instance of {@link SaveDomainToXmlByFasterXml }
     * 
     */
    public SaveDomainToXmlByFasterXml createSaveDomainToXmlByFasterXml() {
        return new SaveDomainToXmlByFasterXml();
    }

    /**
     * Create an instance of {@link SaveDomainToXmlByFasterXmlResponse }
     * 
     */
    public SaveDomainToXmlByFasterXmlResponse createSaveDomainToXmlByFasterXmlResponse() {
        return new SaveDomainToXmlByFasterXmlResponse();
    }

    /**
     * Create an instance of {@link SaveDomainToXmlByJaxB }
     * 
     */
    public SaveDomainToXmlByJaxB createSaveDomainToXmlByJaxB() {
        return new SaveDomainToXmlByJaxB();
    }

    /**
     * Create an instance of {@link SaveDomainToXmlByJaxBResponse }
     * 
     */
    public SaveDomainToXmlByJaxBResponse createSaveDomainToXmlByJaxBResponse() {
        return new SaveDomainToXmlByJaxBResponse();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "ClassNotFoundException")
    public JAXBElement<ClassNotFoundException> createClassNotFoundException(ClassNotFoundException value) {
        return new JAXBElement<ClassNotFoundException>(_ClassNotFoundException_QNAME, ClassNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JAXBException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "JAXBException")
    public JAXBElement<JAXBException> createJAXBException(JAXBException value) {
        return new JAXBElement<JAXBException>(_JAXBException_QNAME, JAXBException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJavaSerializedFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJavaSerializedFile")
    public JAXBElement<RestoreDomainFromJavaSerializedFile> createRestoreDomainFromJavaSerializedFile(RestoreDomainFromJavaSerializedFile value) {
        return new JAXBElement<RestoreDomainFromJavaSerializedFile>(_RestoreDomainFromJavaSerializedFile_QNAME, RestoreDomainFromJavaSerializedFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJavaSerializedFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJavaSerializedFileResponse")
    public JAXBElement<RestoreDomainFromJavaSerializedFileResponse> createRestoreDomainFromJavaSerializedFileResponse(RestoreDomainFromJavaSerializedFileResponse value) {
        return new JAXBElement<RestoreDomainFromJavaSerializedFileResponse>(_RestoreDomainFromJavaSerializedFileResponse_QNAME, RestoreDomainFromJavaSerializedFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJsonByFasterXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJsonByFasterXml")
    public JAXBElement<RestoreDomainFromJsonByFasterXml> createRestoreDomainFromJsonByFasterXml(RestoreDomainFromJsonByFasterXml value) {
        return new JAXBElement<RestoreDomainFromJsonByFasterXml>(_RestoreDomainFromJsonByFasterXml_QNAME, RestoreDomainFromJsonByFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJsonByFasterXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJsonByFasterXmlResponse")
    public JAXBElement<RestoreDomainFromJsonByFasterXmlResponse> createRestoreDomainFromJsonByFasterXmlResponse(RestoreDomainFromJsonByFasterXmlResponse value) {
        return new JAXBElement<RestoreDomainFromJsonByFasterXmlResponse>(_RestoreDomainFromJsonByFasterXmlResponse_QNAME, RestoreDomainFromJsonByFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJsonByJaxB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJsonByJaxB")
    public JAXBElement<RestoreDomainFromJsonByJaxB> createRestoreDomainFromJsonByJaxB(RestoreDomainFromJsonByJaxB value) {
        return new JAXBElement<RestoreDomainFromJsonByJaxB>(_RestoreDomainFromJsonByJaxB_QNAME, RestoreDomainFromJsonByJaxB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromJsonByJaxBResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromJsonByJaxBResponse")
    public JAXBElement<RestoreDomainFromJsonByJaxBResponse> createRestoreDomainFromJsonByJaxBResponse(RestoreDomainFromJsonByJaxBResponse value) {
        return new JAXBElement<RestoreDomainFromJsonByJaxBResponse>(_RestoreDomainFromJsonByJaxBResponse_QNAME, RestoreDomainFromJsonByJaxBResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromXmlByFasterXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromXmlByFasterXml")
    public JAXBElement<RestoreDomainFromXmlByFasterXml> createRestoreDomainFromXmlByFasterXml(RestoreDomainFromXmlByFasterXml value) {
        return new JAXBElement<RestoreDomainFromXmlByFasterXml>(_RestoreDomainFromXmlByFasterXml_QNAME, RestoreDomainFromXmlByFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromXmlByFasterXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromXmlByFasterXmlResponse")
    public JAXBElement<RestoreDomainFromXmlByFasterXmlResponse> createRestoreDomainFromXmlByFasterXmlResponse(RestoreDomainFromXmlByFasterXmlResponse value) {
        return new JAXBElement<RestoreDomainFromXmlByFasterXmlResponse>(_RestoreDomainFromXmlByFasterXmlResponse_QNAME, RestoreDomainFromXmlByFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromXmlByJaxB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromXmlByJaxB")
    public JAXBElement<RestoreDomainFromXmlByJaxB> createRestoreDomainFromXmlByJaxB(RestoreDomainFromXmlByJaxB value) {
        return new JAXBElement<RestoreDomainFromXmlByJaxB>(_RestoreDomainFromXmlByJaxB_QNAME, RestoreDomainFromXmlByJaxB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestoreDomainFromXmlByJaxBResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "restoreDomainFromXmlByJaxBResponse")
    public JAXBElement<RestoreDomainFromXmlByJaxBResponse> createRestoreDomainFromXmlByJaxBResponse(RestoreDomainFromXmlByJaxBResponse value) {
        return new JAXBElement<RestoreDomainFromXmlByJaxBResponse>(_RestoreDomainFromXmlByJaxBResponse_QNAME, RestoreDomainFromXmlByJaxBResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainByJavaSerialization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainByJavaSerialization")
    public JAXBElement<SaveDomainByJavaSerialization> createSaveDomainByJavaSerialization(SaveDomainByJavaSerialization value) {
        return new JAXBElement<SaveDomainByJavaSerialization>(_SaveDomainByJavaSerialization_QNAME, SaveDomainByJavaSerialization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainByJavaSerializationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainByJavaSerializationResponse")
    public JAXBElement<SaveDomainByJavaSerializationResponse> createSaveDomainByJavaSerializationResponse(SaveDomainByJavaSerializationResponse value) {
        return new JAXBElement<SaveDomainByJavaSerializationResponse>(_SaveDomainByJavaSerializationResponse_QNAME, SaveDomainByJavaSerializationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToJsonByFasterXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToJsonByFasterXml")
    public JAXBElement<SaveDomainToJsonByFasterXml> createSaveDomainToJsonByFasterXml(SaveDomainToJsonByFasterXml value) {
        return new JAXBElement<SaveDomainToJsonByFasterXml>(_SaveDomainToJsonByFasterXml_QNAME, SaveDomainToJsonByFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToJsonByFasterXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToJsonByFasterXmlResponse")
    public JAXBElement<SaveDomainToJsonByFasterXmlResponse> createSaveDomainToJsonByFasterXmlResponse(SaveDomainToJsonByFasterXmlResponse value) {
        return new JAXBElement<SaveDomainToJsonByFasterXmlResponse>(_SaveDomainToJsonByFasterXmlResponse_QNAME, SaveDomainToJsonByFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToJsonByJaxB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToJsonByJaxB")
    public JAXBElement<SaveDomainToJsonByJaxB> createSaveDomainToJsonByJaxB(SaveDomainToJsonByJaxB value) {
        return new JAXBElement<SaveDomainToJsonByJaxB>(_SaveDomainToJsonByJaxB_QNAME, SaveDomainToJsonByJaxB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToJsonByJaxBResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToJsonByJaxBResponse")
    public JAXBElement<SaveDomainToJsonByJaxBResponse> createSaveDomainToJsonByJaxBResponse(SaveDomainToJsonByJaxBResponse value) {
        return new JAXBElement<SaveDomainToJsonByJaxBResponse>(_SaveDomainToJsonByJaxBResponse_QNAME, SaveDomainToJsonByJaxBResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToXmlByFasterXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToXmlByFasterXml")
    public JAXBElement<SaveDomainToXmlByFasterXml> createSaveDomainToXmlByFasterXml(SaveDomainToXmlByFasterXml value) {
        return new JAXBElement<SaveDomainToXmlByFasterXml>(_SaveDomainToXmlByFasterXml_QNAME, SaveDomainToXmlByFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToXmlByFasterXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToXmlByFasterXmlResponse")
    public JAXBElement<SaveDomainToXmlByFasterXmlResponse> createSaveDomainToXmlByFasterXmlResponse(SaveDomainToXmlByFasterXmlResponse value) {
        return new JAXBElement<SaveDomainToXmlByFasterXmlResponse>(_SaveDomainToXmlByFasterXmlResponse_QNAME, SaveDomainToXmlByFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToXmlByJaxB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToXmlByJaxB")
    public JAXBElement<SaveDomainToXmlByJaxB> createSaveDomainToXmlByJaxB(SaveDomainToXmlByJaxB value) {
        return new JAXBElement<SaveDomainToXmlByJaxB>(_SaveDomainToXmlByJaxB_QNAME, SaveDomainToXmlByJaxB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDomainToXmlByJaxBResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tmserver.karelin.ru/", name = "saveDomainToXmlByJaxBResponse")
    public JAXBElement<SaveDomainToXmlByJaxBResponse> createSaveDomainToXmlByJaxBResponse(SaveDomainToXmlByJaxBResponse value) {
        return new JAXBElement<SaveDomainToXmlByJaxBResponse>(_SaveDomainToXmlByJaxBResponse_QNAME, SaveDomainToXmlByJaxBResponse.class, null, value);
    }

}
