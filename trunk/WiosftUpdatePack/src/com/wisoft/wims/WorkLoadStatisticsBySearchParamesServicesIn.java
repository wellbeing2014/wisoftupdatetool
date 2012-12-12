package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for workLoadStatisticsBySearchParamesServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;workLoadStatisticsBySearchParamesServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;proinfo_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search_keyword&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search_state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_end_time_from&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_end_time_to&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_origin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_owner&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;task_state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
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
@XmlType(name = "workLoadStatisticsBySearchParamesServicesIn", propOrder = {
		"proinfoId", "searchKeyword", "searchState", "taskEndTimeFrom",
		"taskEndTimeTo", "taskOrigin", "taskOwner", "taskState", "xmjd" })
public class WorkLoadStatisticsBySearchParamesServicesIn {

	@XmlElement(name = "proinfo_id")
	protected String proinfoId;
	@XmlElement(name = "search_keyword")
	protected String searchKeyword;
	@XmlElement(name = "search_state")
	protected String searchState;
	@XmlElement(name = "task_end_time_from")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskEndTimeFrom;
	@XmlElement(name = "task_end_time_to")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar taskEndTimeTo;
	@XmlElement(name = "task_origin")
	protected String taskOrigin;
	@XmlElement(name = "task_owner")
	protected String taskOwner;
	@XmlElement(name = "task_state")
	protected String taskState;
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
