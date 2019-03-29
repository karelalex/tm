
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
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "userId",
    "tasksProjectId",
    "sortField",
    "isOrderStraight"
})
public class GetSortedTaskListByProjectId {

    protected String userId;
    protected String tasksProjectId;
    protected String sortField;
    protected boolean isOrderStraight;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
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
