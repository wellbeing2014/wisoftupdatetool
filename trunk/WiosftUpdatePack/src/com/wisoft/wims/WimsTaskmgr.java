package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsTaskmgr complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsTaskmgr&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;milestone_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proinfo_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proinfo_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_begin_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_create_person&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_create_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_difficulty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_end_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_gjys&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *         &lt;element name=&quot;task_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_isdelete&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;task_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_number&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_origin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_owner&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_plan_begin_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_plan_end_date&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_plan_end_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
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
@XmlType(name = "wimsTaskmgr", propOrder = { "milestoneId", "proinfoId",
		"proinfoName", "taskBeginTime", "taskCreatePerson", "taskCreateTime",
		"taskDifficulty", "taskEndTime", "taskGjys", "taskId", "taskIsdelete",
		"taskName", "taskNumber", "taskOrigin", "taskOwner",
		"taskPlanBeginTime", "taskPlanEndDate", "taskPlanEndTime",
		"taskPriority", "taskRemark", "taskState", "taskWorkload", "trackId",
		"xmjd" })
public class WimsTaskmgr {

	@XmlElement(name = "milestone_id")
	protected String milestoneId;
	@XmlElement(name = "proinfo_id")
	protected String proinfoId;
	@XmlElement(name = "proinfo_name")
	protected String proinfoName;
	@XmlElement(name = "task_begin_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskBeginTime;
	@XmlElement(name = "task_create_person")
	protected String taskCreatePerson;
	@XmlElement(name = "task_create_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskCreateTime;
	@XmlElement(name = "task_difficulty")
	protected String taskDifficulty;
	@XmlElement(name = "task_end_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskEndTime;
	@XmlElement(name = "task_gjys")
	protected float taskGjys;
	@XmlElement(name = "task_id")
	protected String taskId;
	@XmlElement(name = "task_isdelete")
	protected int taskIsdelete;
	@XmlElement(name = "task_name")
	protected String taskName;
	@XmlElement(name = "task_number")
	protected String taskNumber;
	@XmlElement(name = "task_origin")
	protected String taskOrigin;
	@XmlElement(name = "task_owner")
	protected String taskOwner;
	@XmlElement(name = "task_plan_begin_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskPlanBeginTime;
	@XmlElement(name = "task_plan_end_date")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskPlanEndDate;
	@XmlElement(name = "task_plan_end_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskPlanEndTime;
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
	 * Gets the value of the milestoneId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMilestoneId() {
		return milestoneId;
	}

	/**
	 * Sets the value of the milestoneId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMilestoneId(String value) {
		this.milestoneId = value;
	}

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
	 * Gets the value of the proinfoName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProinfoName() {
		return proinfoName;
	}

	/**
	 * Sets the value of the proinfoName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProinfoName(String value) {
		this.proinfoName = value;
	}

	/**
	 * Gets the value of the taskBeginTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskBeginTime() {
		return taskBeginTime;
	}

	/**
	 * Sets the value of the taskBeginTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskBeginTime(XMLGregorianCalendar value) {
		this.taskBeginTime = value;
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
	 * Gets the value of the taskEndTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskEndTime() {
		return taskEndTime;
	}

	/**
	 * Sets the value of the taskEndTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskEndTime(XMLGregorianCalendar value) {
		this.taskEndTime = value;
	}

	/**
	 * Gets the value of the taskGjys property.
	 * 
	 */
	public float getTaskGjys() {
		return taskGjys;
	}

	/**
	 * Sets the value of the taskGjys property.
	 * 
	 */
	public void setTaskGjys(float value) {
		this.taskGjys = value;
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
	 * Gets the value of the taskIsdelete property.
	 * 
	 */
	public int getTaskIsdelete() {
		return taskIsdelete;
	}

	/**
	 * Sets the value of the taskIsdelete property.
	 * 
	 */
	public void setTaskIsdelete(int value) {
		this.taskIsdelete = value;
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
	 * Gets the value of the taskPlanBeginTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskPlanBeginTime() {
		return taskPlanBeginTime;
	}

	/**
	 * Sets the value of the taskPlanBeginTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskPlanBeginTime(XMLGregorianCalendar value) {
		this.taskPlanBeginTime = value;
	}

	/**
	 * Gets the value of the taskPlanEndDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskPlanEndDate() {
		return taskPlanEndDate;
	}

	/**
	 * Sets the value of the taskPlanEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskPlanEndDate(XMLGregorianCalendar value) {
		this.taskPlanEndDate = value;
	}

	/**
	 * Gets the value of the taskPlanEndTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTaskPlanEndTime() {
		return taskPlanEndTime;
	}

	/**
	 * Sets the value of the taskPlanEndTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTaskPlanEndTime(XMLGregorianCalendar value) {
		this.taskPlanEndTime = value;
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
