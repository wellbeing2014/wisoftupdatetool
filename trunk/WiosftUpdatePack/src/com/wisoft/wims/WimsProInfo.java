package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsProInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsProInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;bbkwz&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;bz&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;cilent_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;isdelete&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;kffzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ordernum&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;proname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prosmpname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prousernameid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sfht&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sfys&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;swfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmbh&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmfl&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmjd&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmlrr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmlrrq&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmsxrq&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmysrq&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmzt&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wimsProInfo", propOrder = { "bbkwz", "bz", "cilentId", "id",
		"isdelete", "kffzr", "ordernum", "proname", "prosmpname",
		"prousernameid", "sfht", "sfys", "swfzr", "xmbh", "xmfl", "xmfzr",
		"xmjd", "xmlrr", "xmlrrq", "xmsxrq", "xmysrq", "xmzt" })
public class WimsProInfo {

	protected String bbkwz;
	protected String bz;
	@XmlElement(name = "cilent_id")
	protected String cilentId;
	protected String id;
	protected String isdelete;
	protected String kffzr;
	protected int ordernum;
	protected String proname;
	protected String prosmpname;
	protected String prousernameid;
	protected String sfht;
	protected String sfys;
	protected String swfzr;
	protected String xmbh;
	protected String xmfl;
	protected String xmfzr;
	protected String xmjd;
	protected String xmlrr;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmlrrq;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmsxrq;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmysrq;
	protected String xmzt;

	/**
	 * Gets the value of the bbkwz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBbkwz() {
		return bbkwz;
	}

	/**
	 * Sets the value of the bbkwz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBbkwz(String value) {
		this.bbkwz = value;
	}

	/**
	 * Gets the value of the bz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * Sets the value of the bz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBz(String value) {
		this.bz = value;
	}

	/**
	 * Gets the value of the cilentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCilentId() {
		return cilentId;
	}

	/**
	 * Sets the value of the cilentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCilentId(String value) {
		this.cilentId = value;
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
	 * Gets the value of the isdelete property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsdelete() {
		return isdelete;
	}

	/**
	 * Sets the value of the isdelete property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsdelete(String value) {
		this.isdelete = value;
	}

	/**
	 * Gets the value of the kffzr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKffzr() {
		return kffzr;
	}

	/**
	 * Sets the value of the kffzr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKffzr(String value) {
		this.kffzr = value;
	}

	/**
	 * Gets the value of the ordernum property.
	 * 
	 */
	public int getOrdernum() {
		return ordernum;
	}

	/**
	 * Sets the value of the ordernum property.
	 * 
	 */
	public void setOrdernum(int value) {
		this.ordernum = value;
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
	 * Gets the value of the prosmpname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProsmpname() {
		return prosmpname;
	}

	/**
	 * Sets the value of the prosmpname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProsmpname(String value) {
		this.prosmpname = value;
	}

	/**
	 * Gets the value of the prousernameid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProusernameid() {
		return prousernameid;
	}

	/**
	 * Sets the value of the prousernameid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProusernameid(String value) {
		this.prousernameid = value;
	}

	/**
	 * Gets the value of the sfht property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSfht() {
		return sfht;
	}

	/**
	 * Sets the value of the sfht property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSfht(String value) {
		this.sfht = value;
	}

	/**
	 * Gets the value of the sfys property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSfys() {
		return sfys;
	}

	/**
	 * Sets the value of the sfys property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSfys(String value) {
		this.sfys = value;
	}

	/**
	 * Gets the value of the swfzr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSwfzr() {
		return swfzr;
	}

	/**
	 * Sets the value of the swfzr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSwfzr(String value) {
		this.swfzr = value;
	}

	/**
	 * Gets the value of the xmbh property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmbh() {
		return xmbh;
	}

	/**
	 * Sets the value of the xmbh property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmbh(String value) {
		this.xmbh = value;
	}

	/**
	 * Gets the value of the xmfl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmfl() {
		return xmfl;
	}

	/**
	 * Sets the value of the xmfl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmfl(String value) {
		this.xmfl = value;
	}

	/**
	 * Gets the value of the xmfzr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmfzr() {
		return xmfzr;
	}

	/**
	 * Sets the value of the xmfzr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmfzr(String value) {
		this.xmfzr = value;
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

	/**
	 * Gets the value of the xmlrr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlrr() {
		return xmlrr;
	}

	/**
	 * Sets the value of the xmlrr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlrr(String value) {
		this.xmlrr = value;
	}

	/**
	 * Gets the value of the xmlrrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmlrrq() {
		return xmlrrq;
	}

	/**
	 * Sets the value of the xmlrrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmlrrq(XMLGregorianCalendar value) {
		this.xmlrrq = value;
	}

	/**
	 * Gets the value of the xmsxrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmsxrq() {
		return xmsxrq;
	}

	/**
	 * Sets the value of the xmsxrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmsxrq(XMLGregorianCalendar value) {
		this.xmsxrq = value;
	}

	/**
	 * Gets the value of the xmysrq property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmysrq() {
		return xmysrq;
	}

	/**
	 * Sets the value of the xmysrq property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmysrq(XMLGregorianCalendar value) {
		this.xmysrq = value;
	}

	/**
	 * Gets the value of the xmzt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmzt() {
		return xmzt;
	}

	/**
	 * Sets the value of the xmzt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmzt(String value) {
		this.xmzt = value;
	}

}
