package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for proInfoServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;proInfoServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;cilent_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;keyword&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;kffzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prosmpname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;prousernameid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sfht&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sfys&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;state&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;swfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmbh&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmfl&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmjd&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmsxrq_begin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmsxrq_end&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmysrq_begin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmysrq_end&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
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
@XmlType(name = "proInfoServicesIn", propOrder = { "cilentId", "keyword",
		"kffzr", "proname", "prosmpname", "prousernameid", "sfht", "sfys",
		"state", "swfzr", "xmbh", "xmfl", "xmfzr", "xmjd", "xmsxrqBegin",
		"xmsxrqEnd", "xmysrqBegin", "xmysrqEnd", "xmzt" })
public class ProInfoServicesIn {

	@XmlElement(name = "cilent_id")
	protected String cilentId;
	protected String keyword;
	protected String kffzr;
	protected String proname;
	protected String prosmpname;
	protected String prousernameid;
	protected String sfht;
	protected String sfys;
	protected String state;
	protected String swfzr;
	protected String xmbh;
	protected String xmfl;
	protected String xmfzr;
	protected String xmjd;
	@XmlElement(name = "xmsxrq_begin")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmsxrqBegin;
	@XmlElement(name = "xmsxrq_end")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmsxrqEnd;
	@XmlElement(name = "xmysrq_begin")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmysrqBegin;
	@XmlElement(name = "xmysrq_end")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar xmysrqEnd;
	protected String xmzt;

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
	 * Gets the value of the keyword property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the value of the keyword property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKeyword(String value) {
		this.keyword = value;
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
	 * Gets the value of the xmsxrqBegin property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmsxrqBegin() {
		return xmsxrqBegin;
	}

	/**
	 * Sets the value of the xmsxrqBegin property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmsxrqBegin(XMLGregorianCalendar value) {
		this.xmsxrqBegin = value;
	}

	/**
	 * Gets the value of the xmsxrqEnd property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmsxrqEnd() {
		return xmsxrqEnd;
	}

	/**
	 * Sets the value of the xmsxrqEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmsxrqEnd(XMLGregorianCalendar value) {
		this.xmsxrqEnd = value;
	}

	/**
	 * Gets the value of the xmysrqBegin property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmysrqBegin() {
		return xmysrqBegin;
	}

	/**
	 * Sets the value of the xmysrqBegin property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmysrqBegin(XMLGregorianCalendar value) {
		this.xmysrqBegin = value;
	}

	/**
	 * Gets the value of the xmysrqEnd property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getXmysrqEnd() {
		return xmysrqEnd;
	}

	/**
	 * Sets the value of the xmysrqEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setXmysrqEnd(XMLGregorianCalendar value) {
		this.xmysrqEnd = value;
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
