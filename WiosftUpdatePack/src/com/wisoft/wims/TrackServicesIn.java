package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for trackServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;trackServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;contype&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fact_date_begin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fact_date_end&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fpqk&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;level&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;plan_date_begin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;plan_date_end&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prono&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;search&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqdate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqdate1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqpersonid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zrpersonid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackServicesIn", propOrder = { "contype", "factDateBegin",
		"factDateEnd", "fpqk", "level", "planDateBegin", "planDateEnd",
		"proid", "prono", "search", "sqdate", "sqdate1", "sqpersonid", "state",
		"status", "zrpersonid" })
public class TrackServicesIn {

	protected String contype;
	@XmlElement(name = "fact_date_begin")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar factDateBegin;
	@XmlElement(name = "fact_date_end")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar factDateEnd;
	protected String fpqk;
	protected String level;
	@XmlElement(name = "plan_date_begin")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar planDateBegin;
	@XmlElement(name = "plan_date_end")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar planDateEnd;
	protected String proid;
	protected String prono;
	protected String search;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar sqdate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar sqdate1;
	protected String sqpersonid;
	protected String state;
	protected String status;
	protected String zrpersonid;

	/**
	 * Gets the value of the contype property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContype() {
		return contype;
	}

	/**
	 * Sets the value of the contype property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContype(String value) {
		this.contype = value;
	}

	/**
	 * Gets the value of the factDateBegin property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFactDateBegin() {
		return factDateBegin;
	}

	/**
	 * Sets the value of the factDateBegin property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFactDateBegin(XMLGregorianCalendar value) {
		this.factDateBegin = value;
	}

	/**
	 * Gets the value of the factDateEnd property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFactDateEnd() {
		return factDateEnd;
	}

	/**
	 * Sets the value of the factDateEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFactDateEnd(XMLGregorianCalendar value) {
		this.factDateEnd = value;
	}

	/**
	 * Gets the value of the fpqk property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFpqk() {
		return fpqk;
	}

	/**
	 * Sets the value of the fpqk property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFpqk(String value) {
		this.fpqk = value;
	}

	/**
	 * Gets the value of the level property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Sets the value of the level property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLevel(String value) {
		this.level = value;
	}

	/**
	 * Gets the value of the planDateBegin property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPlanDateBegin() {
		return planDateBegin;
	}

	/**
	 * Sets the value of the planDateBegin property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPlanDateBegin(XMLGregorianCalendar value) {
		this.planDateBegin = value;
	}

	/**
	 * Gets the value of the planDateEnd property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPlanDateEnd() {
		return planDateEnd;
	}

	/**
	 * Sets the value of the planDateEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPlanDateEnd(XMLGregorianCalendar value) {
		this.planDateEnd = value;
	}

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
	 * Gets the value of the prono property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProno() {
		return prono;
	}

	/**
	 * Sets the value of the prono property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProno(String value) {
		this.prono = value;
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
	 * Gets the value of the sqpersonid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSqpersonid() {
		return sqpersonid;
	}

	/**
	 * Sets the value of the sqpersonid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSqpersonid(String value) {
		this.sqpersonid = value;
	}

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setState(String value) {
		this.state = value;
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

	/**
	 * Gets the value of the zrpersonid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZrpersonid() {
		return zrpersonid;
	}

	/**
	 * Sets the value of the zrpersonid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZrpersonid(String value) {
		this.zrpersonid = value;
	}

}
