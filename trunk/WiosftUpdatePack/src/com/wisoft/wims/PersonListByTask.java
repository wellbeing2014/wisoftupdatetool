package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for personListByTask complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;personListByTask&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;checked&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;personId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;personName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsTaskmgr&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;taskId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;trackId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personListByTask", propOrder = { "checked", "personId",
		"personName", "task", "taskId", "trackId" })
public class PersonListByTask {

	protected boolean checked;
	protected String personId;
	protected String personName;
	protected WimsTaskmgr task;
	protected String taskId;
	protected String trackId;

	/**
	 * Gets the value of the checked property.
	 * 
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the value of the checked property.
	 * 
	 */
	public void setChecked(boolean value) {
		this.checked = value;
	}

	/**
	 * Gets the value of the personId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * Sets the value of the personId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPersonId(String value) {
		this.personId = value;
	}

	/**
	 * Gets the value of the personName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * Sets the value of the personName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPersonName(String value) {
		this.personName = value;
	}

	/**
	 * Gets the value of the task property.
	 * 
	 * @return possible object is {@link WimsTaskmgr }
	 * 
	 */
	public WimsTaskmgr getTask() {
		return task;
	}

	/**
	 * Sets the value of the task property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsTaskmgr }
	 * 
	 */
	public void setTask(WimsTaskmgr value) {
		this.task = value;
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

	/**
	 * Gets the value of the trackId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrackId() {
		return trackId;
	}

	/**
	 * Sets the value of the trackId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrackId(String value) {
		this.trackId = value;
	}

}
