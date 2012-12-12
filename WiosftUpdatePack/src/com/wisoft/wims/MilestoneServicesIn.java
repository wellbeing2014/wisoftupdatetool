package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for milestoneServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;milestoneServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;proid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqdate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqdate1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "milestoneServicesIn", propOrder = { "proid", "proname",
		"search", "sqdate", "sqdate1", "status" })
public class MilestoneServicesIn {

	protected String proid;
	protected String proname;
	protected String search;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar sqdate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar sqdate1;
	protected String status;

	/**
	 * Gets the value of the proid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProid() {
		return proid;
	}

	/**
	 * Sets the value of the proid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProid(String value) {
		this.proid = value;
	}

	/**
	 * Gets the value of the proname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProname() {
		return proname;
	}

	/**
	 * Sets the value of the proname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProname(String value) {
		this.proname = value;
	}

	/**
	 * Gets the value of the search property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Sets the value of the search property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSearch(String value) {
		this.search = value;
	}

	/**
	 * Gets the value of the sqdate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSqdate() {
		return sqdate;
	}

	/**
	 * Sets the value of the sqdate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSqdate(XMLGregorianCalendar value) {
		this.sqdate = value;
	}

	/**
	 * Gets the value of the sqdate1 property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSqdate1() {
		return sqdate1;
	}

	/**
	 * Sets the value of the sqdate1 property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSqdate1(XMLGregorianCalendar value) {
		this.sqdate1 = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}
