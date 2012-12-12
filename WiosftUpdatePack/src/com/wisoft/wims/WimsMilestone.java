package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wimsMilestone complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wimsMilestone&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;glrwzs&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;milestone_id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;milestone_name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;milestone_time&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;wcrws&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wimsMilestone", propOrder = { "glrwzs", "milestoneId",
		"milestoneName", "milestoneTime", "proid", "proname", "wcrws" })
public class WimsMilestone {

	protected int glrwzs;
	@XmlElement(name = "milestone_id")
	protected String milestoneId;
	@XmlElement(name = "milestone_name")
	protected String milestoneName;
	@XmlElement(name = "milestone_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar milestoneTime;
	protected String proid;
	protected String proname;
	protected int wcrws;

	/**
	 * Gets the value of the glrwzs property.
	 * 
	 */
	public int getGlrwzs() {
		return glrwzs;
	}

	/**
	 * Sets the value of the glrwzs property.
	 * 
	 */
	public void setGlrwzs(int value) {
		this.glrwzs = value;
	}

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
	 * Gets the value of the milestoneName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMilestoneName() {
		return milestoneName;
	}

	/**
	 * Sets the value of the milestoneName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMilestoneName(String value) {
		this.milestoneName = value;
	}

	/**
	 * Gets the value of the milestoneTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getMilestoneTime() {
		return milestoneTime;
	}

	/**
	 * Sets the value of the milestoneTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setMilestoneTime(XMLGregorianCalendar value) {
		this.milestoneTime = value;
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
	 * Gets the value of the wcrws property.
	 * 
	 */
	public int getWcrws() {
		return wcrws;
	}

	/**
	 * Sets the value of the wcrws property.
	 * 
	 */
	public void setWcrws(int value) {
		this.wcrws = value;
	}

}
