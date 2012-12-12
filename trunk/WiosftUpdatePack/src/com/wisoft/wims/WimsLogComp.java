package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsLogComp complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsLogComp&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;newcontent&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;oldcontent&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;oppersonid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;optime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;propname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;trackid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wimsLogComp", propOrder = { "id", "newcontent", "oldcontent",
		"oppersonid", "optime", "propname", "trackid" })
public class WimsLogComp {

	protected String id;
	protected String newcontent;
	protected String oldcontent;
	protected String oppersonid;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar optime;
	protected String propname;
	protected String trackid;

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
	 * Gets the value of the newcontent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNewcontent() {
		return newcontent;
	}

	/**
	 * Sets the value of the newcontent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNewcontent(String value) {
		this.newcontent = value;
	}

	/**
	 * Gets the value of the oldcontent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOldcontent() {
		return oldcontent;
	}

	/**
	 * Sets the value of the oldcontent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOldcontent(String value) {
		this.oldcontent = value;
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
	 * Gets the value of the propname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPropname() {
		return propname;
	}

	/**
	 * Sets the value of the propname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPropname(String value) {
		this.propname = value;
	}

	/**
	 * Gets the value of the trackid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrackid() {
		return trackid;
	}

	/**
	 * Sets the value of the trackid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrackid(String value) {
		this.trackid = value;
	}

}
