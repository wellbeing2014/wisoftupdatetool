package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsProInfoHistory complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsProInfoHistory&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;change_column&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_column_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_person&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_person_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;change_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;new_content&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;old_content&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proinfo_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wimsProInfoHistory", propOrder = { "changeColumn",
		"changeColumnName", "changePerson", "changePersonName", "changeTime",
		"id", "newContent", "oldContent", "proinfoId" })
public class WimsProInfoHistory {

	@XmlElement(name = "change_column")
	protected String changeColumn;
	@XmlElement(name = "change_column_name")
	protected String changeColumnName;
	@XmlElement(name = "change_person")
	protected String changePerson;
	@XmlElement(name = "change_person_name")
	protected String changePersonName;
	@XmlElement(name = "change_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar changeTime;
	protected String id;
	@XmlElement(name = "new_content")
	protected String newContent;
	@XmlElement(name = "old_content")
	protected String oldContent;
	@XmlElement(name = "proinfo_id")
	protected String proinfoId;

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
	 * Gets the value of the changeColumnName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChangeColumnName() {
		return changeColumnName;
	}

	/**
	 * Sets the value of the changeColumnName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChangeColumnName(String value) {
		this.changeColumnName = value;
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
	 * Gets the value of the changePersonName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChangePersonName() {
		return changePersonName;
	}

	/**
	 * Sets the value of the changePersonName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChangePersonName(String value) {
		this.changePersonName = value;
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
	 * Gets the value of the newContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNewContent() {
		return newContent;
	}

	/**
	 * Sets the value of the newContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNewContent(String value) {
		this.newContent = value;
	}

	/**
	 * Gets the value of the oldContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOldContent() {
		return oldContent;
	}

	/**
	 * Sets the value of the oldContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOldContent(String value) {
		this.oldContent = value;
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

}
