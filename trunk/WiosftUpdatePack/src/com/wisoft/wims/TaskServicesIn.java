package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for taskServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;taskServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;proinfo_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search_keyword&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search_state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_begin_time_from&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_begin_time_to&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_create_person&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_create_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_difficulty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_end_time_from&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_end_time_to&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_number&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_origin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_owner&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_priority&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_remark&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_workload&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *         &lt;element name=&quot;track_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmjd&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskServicesIn", propOrder = { "proinfoId", "searchKeyword",
		"searchState", "taskBeginTimeFrom", "taskBeginTimeTo",
		"taskCreatePerson", "taskCreateTime", "taskDifficulty",
		"taskEndTimeFrom", "taskEndTimeTo", "taskName", "taskNumber",
		"taskOrigin", "taskOwner", "taskPriority", "taskRemark", "taskState",
		"taskWorkload", "trackId", "xmjd" })
public class TaskServicesIn {

	@XmlElement(name = "proinfo_id")
	protected String proinfoId;
	@XmlElement(name = "search_keyword")
	protected String searchKeyword;
	@XmlElement(name = "search_state")
	protected String searchState;
	@XmlElement(name = "task_begin_time_from")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskBeginTimeFrom;
	@XmlElement(name = "task_begin_time_to")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskBeginTimeTo;
	@XmlElement(name = "task_create_person")
	protected String taskCreatePerson;
	@XmlElement(name = "task_create_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskCreateTime;
	@XmlElement(name = "task_difficulty")
	protected String taskDifficulty;
	@XmlElement(name = "task_end_time_from")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskEndTimeFrom;
	@XmlElement(name = "task_end_time_to")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskEndTimeTo;
	@XmlElement(name = "task_name")
	protected String taskName;
	@XmlElement(name = "task_number")
	protected String taskNumber;
	@XmlElement(name = "task_origin")
	protected String taskOrigin;
	@XmlElement(name = "task_owner")
	protected String taskOwner;
	@XmlElement(name = "task_priority")
	protected String taskPriority;
	@XmlElement(name = "task_remark")
	protected String taskRemark;
	@XmlElement(name = "task_state")
	protected String taskState;
	@XmlElement(name = "task_workload")
	protected float taskWorkload;
	@XmlElement(name = "track_id")
	protected String trackId;
	protected String xmjd;

	/**
	 * Gets the value of the proinfoId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProinfoId() {
		return proinfoId;
	}

	/**
	 * Sets the value of the proinfoId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProinfoId(String value) {
		this.proinfoId = value;
	}

	/**
	 * Gets the value of the searchKeyword property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * Sets the value of the searchKeyword property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSearchKeyword(String value) {
		this.searchKeyword = value;
	}

	/**
	 * Gets the value of the searchState property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSearchState() {
		return searchState;
	}

	/**
	 * Sets the value of the searchState property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSearchState(String value) {
		this.searchState = value;
	}

	/**
	 * Gets the value of the taskBeginTimeFrom property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskBeginTimeFrom() {
		return taskBeginTimeFrom;
	}

	/**
	 * Sets the value of the taskBeginTimeFrom property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskBeginTimeFrom(XMLGregorianCalendar value) {
		this.taskBeginTimeFrom = value;
	}

	/**
	 * Gets the value of the taskBeginTimeTo property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskBeginTimeTo() {
		return taskBeginTimeTo;
	}

	/**
	 * Sets the value of the taskBeginTimeTo property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskBeginTimeTo(XMLGregorianCalendar value) {
		this.taskBeginTimeTo = value;
	}

	/**
	 * Gets the value of the taskCreatePerson property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskCreatePerson() {
		return taskCreatePerson;
	}

	/**
	 * Sets the value of the taskCreatePerson property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskCreatePerson(String value) {
		this.taskCreatePerson = value;
	}

	/**
	 * Gets the value of the taskCreateTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskCreateTime() {
		return taskCreateTime;
	}

	/**
	 * Sets the value of the taskCreateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskCreateTime(XMLGregorianCalendar value) {
		this.taskCreateTime = value;
	}

	/**
	 * Gets the value of the taskDifficulty property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskDifficulty() {
		return taskDifficulty;
	}

	/**
	 * Sets the value of the taskDifficulty property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskDifficulty(String value) {
		this.taskDifficulty = value;
	}

	/**
	 * Gets the value of the taskEndTimeFrom property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskEndTimeFrom() {
		return taskEndTimeFrom;
	}

	/**
	 * Sets the value of the taskEndTimeFrom property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskEndTimeFrom(XMLGregorianCalendar value) {
		this.taskEndTimeFrom = value;
	}

	/**
	 * Gets the value of the taskEndTimeTo property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskEndTimeTo() {
		return taskEndTimeTo;
	}

	/**
	 * Sets the value of the taskEndTimeTo property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskEndTimeTo(XMLGregorianCalendar value) {
		this.taskEndTimeTo = value;
	}

	/**
	 * Gets the value of the taskName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the value of the taskName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskName(String value) {
		this.taskName = value;
	}

	/**
	 * Gets the value of the taskNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskNumber() {
		return taskNumber;
	}

	/**
	 * Sets the value of the taskNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskNumber(String value) {
		this.taskNumber = value;
	}

	/**
	 * Gets the value of the taskOrigin property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskOrigin() {
		return taskOrigin;
	}

	/**
	 * Sets the value of the taskOrigin property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskOrigin(String value) {
		this.taskOrigin = value;
	}

	/**
	 * Gets the value of the taskOwner property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskOwner() {
		return taskOwner;
	}

	/**
	 * Sets the value of the taskOwner property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskOwner(String value) {
		this.taskOwner = value;
	}

	/**
	 * Gets the value of the taskPriority property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskPriority() {
		return taskPriority;
	}

	/**
	 * Sets the value of the taskPriority property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskPriority(String value) {
		this.taskPriority = value;
	}

	/**
	 * Gets the value of the taskRemark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskRemark() {
		return taskRemark;
	}

	/**
	 * Sets the value of the taskRemark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskRemark(String value) {
		this.taskRemark = value;
	}

	/**
	 * Gets the value of the taskState property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskState() {
		return taskState;
	}

	/**
	 * Sets the value of the taskState property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskState(String value) {
		this.taskState = value;
	}

	/**
	 * Gets the value of the taskWorkload property.
	 * 
	 */
	public float getTaskWorkload() {
		return taskWorkload;
	}

	/**
	 * Sets the value of the taskWorkload property.
	 * 
	 */
	public void setTaskWorkload(float value) {
		this.taskWorkload = value;
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

	/**
	 * Gets the value of the xmjd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmjd() {
		return xmjd;
	}

	/**
	 * Sets the value of the xmjd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmjd(String value) {
		this.xmjd = value;
	}

}
