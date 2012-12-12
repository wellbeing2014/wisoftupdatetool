package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ownerServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;ownerServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;certify&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;depid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;groupIndex&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ischeck&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;leader&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;positionname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;realname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sex&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ownerServicesIn", propOrder = { "certify", "depid",
		"groupIndex", "id", "ischeck", "leader", "positionname", "realname",
		"sex" })
public class OwnerServicesIn {

	protected Long certify;
	protected String depid;
	protected int groupIndex;
	protected String id;
	protected boolean ischeck;
	protected boolean leader;
	protected String positionname;
	protected String realname;
	protected Long sex;

	/**
	 * Gets the value of the certify property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getCertify() {
		return certify;
	}

	/**
	 * Sets the value of the certify property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setCertify(Long value) {
		this.certify = value;
	}

	/**
	 * Gets the value of the depid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepid() {
		return depid;
	}

	/**
	 * Sets the value of the depid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepid(String value) {
		this.depid = value;
	}

	/**
	 * Gets the value of the groupIndex property.
	 * 
	 */
	public int getGroupIndex() {
		return groupIndex;
	}

	/**
	 * Sets the value of the groupIndex property.
	 * 
	 */
	public void setGroupIndex(int value) {
		this.groupIndex = value;
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
	 * Gets the value of the ischeck property.
	 * 
	 */
	public boolean isIscheck() {
		return ischeck;
	}

	/**
	 * Sets the value of the ischeck property.
	 * 
	 */
	public void setIscheck(boolean value) {
		this.ischeck = value;
	}

	/**
	 * Gets the value of the leader property.
	 * 
	 */
	public boolean isLeader() {
		return leader;
	}

	/**
	 * Sets the value of the leader property.
	 * 
	 */
	public void setLeader(boolean value) {
		this.leader = value;
	}

	/**
	 * Gets the value of the positionname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPositionname() {
		return positionname;
	}

	/**
	 * Sets the value of the positionname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPositionname(String value) {
		this.positionname = value;
	}

	/**
	 * Gets the value of the realname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * Sets the value of the realname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRealname(String value) {
		this.realname = value;
	}

	/**
	 * Gets the value of the sex property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSex() {
		return sex;
	}

	/**
	 * Sets the value of the sex property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSex(Long value) {
		this.sex = value;
	}

}
