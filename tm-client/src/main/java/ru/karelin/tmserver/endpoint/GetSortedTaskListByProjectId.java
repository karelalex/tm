
package ru.karelin.tmserver.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSortedTaskListByProjectId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSortedTaskListByProjectId"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="session" type="{http://endpoint.tmserver.karelin.ru/}sessionDto" minOccurs="0"/&gt;
 *         &lt;element name="tasksProjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sortField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isOrderStraight" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSortedTaskListByProjectId", propOrder = {
    "session",
    "tasksProjectId",
    "sortField",
    "isOrderStraight"
})
public class GetSortedTaskListByProjectId {

    protected SessionDto session;
    protected String tasksProjectId;
    protected String sortField;
    protected boolean isOrderStraight;

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
     * Gets the value of the tasksProjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTasksProjectId() {
        return tasksProjectId;
    }

    /**
     * Sets the value of the tasksProjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTasksProjectId(String value) {
        this.tasksProjectId = value;
    }

    /**
     * Gets the value of the sortField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * Sets the value of the sortField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortField(String value) {
        this.sortField = value;
    }

    /**
     * Gets the value of the isOrderStraight property.
     * 
     */
    public boolean isIsOrderStraight() {
        return isOrderStraight;
    }

    /**
     * Sets the value of the isOrderStraight property.
     * 
     */
    public void setIsOrderStraight(boolean value) {
        this.isOrderStraight = value;
    }

}
