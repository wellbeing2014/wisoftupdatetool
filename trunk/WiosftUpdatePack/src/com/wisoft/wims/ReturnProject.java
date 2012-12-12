package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for returnProject complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;returnProject&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;begin_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;children&quot; type=&quot;{http://www.springframework.org/schema/beans}returnMilestone&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;end_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;is_task&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnProject", propOrder = { "beginTime", "children",
		"endTime", "id", "isTask", "name" })
public class ReturnProject extends BaseReturn {

	@XmlElement(name = "begin_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar beginTime;
	@XmlElement(nillable = true)
	protected List<ReturnMilestone> children;
	@XmlElement(name = "end_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar endTime;
	protected String id;
	@XmlElement(name = "is_task")
	protected boolean isTask;
	protected String name;

	/**
	 * Gets the value of the beginTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the value of the beginTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setBeginTime(XMLGregorianCalendar value) {
		this.beginTime = value;
	}

	/**
	 * Gets the value of the children property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the children property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getChildren().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ReturnMilestone }
	 * 
	 * 
	 */
	public List<ReturnMilestone> getChildren() {
		if (children == null) {
			children = new ArrayList<ReturnMilestone>();
		}
		return this.children;
	}

	/**
	 * Gets the value of the endTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getEndTime() {
		return endTime;
	}

	/**
	 * Sets the value of the endTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setEndTime(XMLGregorianCalendar value) {
		this.endTime = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the isTask property.
	 * 
	 */
	public boolean isIsTask() {
		return isTask;
	}

	/**
	 * Sets the value of the isTask property.
	 * 
	 */
	public void setIsTask(boolean value) {
		this.isTask = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

}
