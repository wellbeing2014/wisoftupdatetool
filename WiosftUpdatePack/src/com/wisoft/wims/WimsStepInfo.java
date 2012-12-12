package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsStepInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsStepInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;content&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;contenttype&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;finishtime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fpqk&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;level&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;lsh&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;notes&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;oppersonid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;optime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;plantime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proinfo_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prono&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqdate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sqpersonid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;tcz&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ver&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;wsitid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
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
@XmlType(name = "wimsStepInfo", propOrder = { "content", "contenttype",
		"finishtime", "fpqk", "id", "level", "lsh", "notes", "oppersonid",
		"optime", "plantime", "proid", "proinfoId", "prono", "sqdate",
		"sqpersonid", "status", "tcz", "ver", "wsitid", "zrpersonid" })
public class WimsStepInfo {

	protected String content;
	protected String contenttype;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar finishtime;
	protected String fpqk;
	protected String id;
	protected String level;
	protected String lsh;
	protected String notes;
	protected String oppersonid;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar optime;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar plantime;
	protected String proid;
	@XmlElement(name = "proinfo_id")
	protected String proinfoId;
	protected String prono;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar sqdate;
	protected String sqpersonid;
	protected String status;
	protected String tcz;
	protected String ver;
	protected String wsitid;
	protected String zrpersonid;

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the contenttype property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContenttype() {
		return contenttype;
	}

	/**
	 * Sets the value of the contenttype property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContenttype(String value) {
		this.contenttype = value;
	}

	/**
	 * Gets the value of the finishtime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFinishtime() {
		return finishtime;
	}

	/**
	 * Sets the value of the finishtime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFinishtime(XMLGregorianCalendar value) {
		this.finishtime = value;
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
	 * Gets the value of the lsh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLsh() {
		return lsh;
	}

	/**
	 * Sets the value of the lsh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLsh(String value) {
		this.lsh = value;
	}

	/**
	 * Gets the value of the notes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the value of the notes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNotes(String value) {
		this.notes = value;
	}

	/**
	 * Gets the value of the oppersonid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOppersonid() {
		return oppersonid;
	}

	/**
	 * Sets the value of the oppersonid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOppersonid(String value) {
		this.oppersonid = value;
	}

	/**
	 * Gets the value of the optime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getOptime() {
		return optime;
	}

	/**
	 * Sets the value of the optime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setOptime(XMLGregorianCalendar value) {
		this.optime = value;
	}

	/**
	 * Gets the value of the plantime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPlantime() {
		return plantime;
	}

	/**
	 * Sets the value of the plantime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPlantime(XMLGregorianCalendar value) {
		this.plantime = value;
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
	 * Gets the value of the tcz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTcz() {
		return tcz;
	}

	/**
	 * Sets the value of the tcz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTcz(String value) {
		this.tcz = value;
	}

	/**
	 * Gets the value of the ver property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVer() {
		return ver;
	}

	/**
	 * Sets the value of the ver property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVer(String value) {
		this.ver = value;
	}

	/**
	 * Gets the value of the wsitid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWsitid() {
		return wsitid;
	}

	/**
	 * Sets the value of the wsitid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWsitid(String value) {
		this.wsitid = value;
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
