
package ru.karelin.tmserver.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for editProject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editProject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="session" type="{http://endpoint.tmserver.karelin.ru/}sessionDto" minOccurs="0"/&gt;
 *         &lt;element name="projectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="projectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="projectDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="projectStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="projectFinishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="projectStatus" type="{http://endpoint.tmserver.karelin.ru/}status" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editProject", propOrder = {
    "session",
    "projectId",
    "projectName",
    "projectDescription",
    "projectStartDate",
    "projectFinishDate",
    "projectStatus"
})
public class EditProject {

    protected SessionDto session;
    protected String projectId;
    protected String projectName;
    protected String projectDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar projectStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar projectFinishDate;
    @XmlSchemaType(name = "string")
    protected Status projectStatus;

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDto }
     *     
     */
    public SessionDto getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDto }
     *     
     */
    public void setSession(SessionDto value) {
        this.session = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectId(String value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the projectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the value of the projectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * Gets the value of the projectDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the value of the projectDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectDescription(String value) {
        this.projectDescription = value;
    }

    /**
     * Gets the value of the projectStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * Sets the value of the projectStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProjectStartDate(XMLGregorianCalendar value) {
        this.projectStartDate = value;
    }

    /**
     * Gets the value of the projectFinishDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProjectFinishDate() {
        return projectFinishDate;
    }

    /**
     * Sets the value of the projectFinishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProjectFinishDate(XMLGregorianCalendar value) {
        this.projectFinishDate = value;
    }

    /**
     * Gets the value of the projectStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getProjectStatus() {
        return projectStatus;
    }

    /**
     * Sets the value of the projectStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setProjectStatus(Status value) {
        this.projectStatus = value;
    }

}
