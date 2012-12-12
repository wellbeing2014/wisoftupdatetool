package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for omsPerson complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;omsPerson&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;birthday&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;business&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;certify&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;depid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;education&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;isvalide&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;kqcode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ldapid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;linkemail&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;linkmobile&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;linktel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;loginname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;modifytime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;nation&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ouid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;partytime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;password&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;pictureid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;positionname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;realname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sex&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;workaddressid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;worktime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zbpy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zzmm&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "omsPerson", propOrder = { "birthday", "business", "certify",
		"depid", "education", "id", "isvalide", "kqcode", "ldapid",
		"linkemail", "linkmobile", "linktel", "loginname", "modifytime",
		"nation", "ouid", "partytime", "password", "pictureid", "positionname",
		"realname", "sex", "workaddressid", "worktime", "zbpy", "zzmm" })
public class OmsPerson {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar birthday;
	protected String business;
	protected Long certify;
	protected String depid;
	protected String education;
	protected String id;
	protected Long isvalide;
	protected String kqcode;
	protected String ldapid;
	protected String linkemail;
	protected String linkmobile;
	protected String linktel;
	protected String loginname;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifytime;
	protected String nation;
	protected String ouid;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar partytime;
	protected String password;
	protected String pictureid;
	protected String positionname;
	protected String realname;
	protected Long sex;
	protected String workaddressid;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar worktime;
	protected Long zbpy;
	protected String zzmm;

	/**
	 * Gets the value of the birthday property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getBirthday() {
		return birthday;
	}

	/**
	 * Sets the value of the birthday property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setBirthday(XMLGregorianCalendar value) {
		this.birthday = value;
	}

	/**
	 * Gets the value of the business property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusiness() {
		return business;
	}

	/**
	 * Sets the value of the business property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusiness(String value) {
		this.business = value;
	}

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
	 * Gets the value of the education property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * Sets the value of the education property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEducation(String value) {
		this.education = value;
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
	 * Gets the value of the isvalide property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getIsvalide() {
		return isvalide;
	}

	/**
	 * Sets the value of the isvalide property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setIsvalide(Long value) {
		this.isvalide = value;
	}

	/**
	 * Gets the value of the kqcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKqcode() {
		return kqcode;
	}

	/**
	 * Sets the value of the kqcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKqcode(String value) {
		this.kqcode = value;
	}

	/**
	 * Gets the value of the ldapid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLdapid() {
		return ldapid;
	}

	/**
	 * Sets the value of the ldapid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLdapid(String value) {
		this.ldapid = value;
	}

	/**
	 * Gets the value of the linkemail property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkemail() {
		return linkemail;
	}

	/**
	 * Sets the value of the linkemail property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkemail(String value) {
		this.linkemail = value;
	}

	/**
	 * Gets the value of the linkmobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkmobile() {
		return linkmobile;
	}

	/**
	 * Sets the value of the linkmobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkmobile(String value) {
		this.linkmobile = value;
	}

	/**
	 * Gets the value of the linktel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinktel() {
		return linktel;
	}

	/**
	 * Sets the value of the linktel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinktel(String value) {
		this.linktel = value;
	}

	/**
	 * Gets the value of the loginname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * Sets the value of the loginname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoginname(String value) {
		this.loginname = value;
	}

	/**
	 * Gets the value of the modifytime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getModifytime() {
		return modifytime;
	}

	/**
	 * Sets the value of the modifytime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setModifytime(XMLGregorianCalendar value) {
		this.modifytime = value;
	}

	/**
	 * Gets the value of the nation property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * Sets the value of the nation property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNation(String value) {
		this.nation = value;
	}

	/**
	 * Gets the value of the ouid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOuid() {
		return ouid;
	}

	/**
	 * Sets the value of the ouid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOuid(String value) {
		this.ouid = value;
	}

	/**
	 * Gets the value of the partytime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getPartytime() {
		return partytime;
	}

	/**
	 * Sets the value of the partytime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setPartytime(XMLGregorianCalendar value) {
		this.partytime = value;
	}

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the pictureid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPictureid() {
		return pictureid;
	}

	/**
	 * Sets the value of the pictureid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPictureid(String value) {
		this.pictureid = value;
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

	/**
	 * Gets the value of the workaddressid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkaddressid() {
		return workaddressid;
	}

	/**
	 * Sets the value of the workaddressid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkaddressid(String value) {
		this.workaddressid = value;
	}

	/**
	 * Gets the value of the worktime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getWorktime() {
		return worktime;
	}

	/**
	 * Sets the value of the worktime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setWorktime(XMLGregorianCalendar value) {
		this.worktime = value;
	}

	/**
	 * Gets the value of the zbpy property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getZbpy() {
		return zbpy;
	}

	/**
	 * Sets the value of the zbpy property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setZbpy(Long value) {
		this.zbpy = value;
	}

	/**
	 * Gets the value of the zzmm property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZzmm() {
		return zzmm;
	}

	/**
	 * Sets the value of the zzmm property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZzmm(String value) {
		this.zzmm = value;
	}

}
