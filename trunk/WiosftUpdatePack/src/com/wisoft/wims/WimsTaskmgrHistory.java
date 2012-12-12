package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsTaskmgrHistory complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsTaskmgrHistory&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;change_column&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_person&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;content_new&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;content_old&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wimsTaskmgrHistory", propOrder = { "changeColumn",
		"changePerson", "changeTime", "contentNew", "contentOld", "id",
		"taskId" })
public class WimsTaskmgrHistory {

	@XmlElement(name = "change_column")
	protected String changeColumn;
	@XmlElement(name = "change_person")
	protected String changePerson;
	@XmlElement(name = "change_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar changeTime;
	@XmlElement(name = "content_new")
	protected String contentNew;
	@XmlElement(name = "content_old")
	protected String contentOld;
	protected String id;
	@XmlElement(name = "task_id")
	protected String taskId;

	/**
	 * Gets the value of the changeColumn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChangeColumn() {
		return changeColumn;
	}

	/**
	 * Sets the value of the changeColumn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChangeColumn(String value) {
		this.changeColumn = value;
	}

	/**
	 * Gets the value of the changePerson property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChangePerson() {
		return changePerson;
	}

	/**
	 * Sets the value of the changePerson property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChangePerson(String value) {
		this.changePerson = value;
	}

	/**
	 * Gets the value of the changeTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getChangeTime() {
		return changeTime;
	}

	/**
	 * Sets the value of the changeTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setChangeTime(XMLGregorianCalendar value) {
		this.changeTime = value;
	}

	/**
	 * Gets the value of the contentNew property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentNew() {
		return contentNew;
	}

	/**
	 * Sets the value of the contentNew property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentNew(String value) {
		this.contentNew = value;
	}

	/**
	 * Gets the value of the contentOld property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentOld() {
		return contentOld;
	}

	/**
	 * Sets the value of the contentOld property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentOld(String value) {
		this.contentOld = value;
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
	 * Gets the value of the taskId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * Sets the value of the taskId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskId(String value) {
		this.taskId = value;
	}

}
