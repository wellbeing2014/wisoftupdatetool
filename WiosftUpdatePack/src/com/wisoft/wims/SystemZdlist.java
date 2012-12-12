package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for systemZdlist complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;systemZdlist&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;mc&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sxh&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;value&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zdcode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zdid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemZdlist", propOrder = { "id", "mc", "sxh", "value",
		"zdcode", "zdid" })
public class SystemZdlist {

	protected String id;
	protected String mc;
	protected Long sxh;
	protected String value;
	protected String zdcode;
	protected String zdid;

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
	 * Gets the value of the mc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * Sets the value of the mc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMc(String value) {
		this.mc = value;
	}

	/**
	 * Gets the value of the sxh property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSxh() {
		return sxh;
	}

	/**
	 * Sets the value of the sxh property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSxh(Long value) {
		this.sxh = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the zdcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZdcode() {
		return zdcode;
	}

	/**
	 * Sets the value of the zdcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZdcode(String value) {
		this.zdcode = value;
	}

	/**
	 * Gets the value of the zdid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZdid() {
		return zdid;
	}

	/**
	 * Sets the value of the zdid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZdid(String value) {
		this.zdid = value;
	}

}
