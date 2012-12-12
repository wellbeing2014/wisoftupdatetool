package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for workLoadStatisticsServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;workLoadStatisticsServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;highTaskCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot;/&gt;
 *         &lt;element name=&quot;highTaskWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *         &lt;element name=&quot;ischeck&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;lowTaskCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot;/&gt;
 *         &lt;element name=&quot;lowTaskWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *         &lt;element name=&quot;mediumTaskCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot;/&gt;
 *         &lt;element name=&quot;mediumTaskWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *         &lt;element name=&quot;realname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;taskCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot;/&gt;
 *         &lt;element name=&quot;taskWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}float&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workLoadStatisticsServicesIn", propOrder = { "highTaskCount",
		"highTaskWorkLoad", "ischeck", "lowTaskCount", "lowTaskWorkLoad",
		"mediumTaskCount", "mediumTaskWorkLoad", "realname", "taskCount",
		"taskWorkLoad" })
public class WorkLoadStatisticsServicesIn {

	protected long highTaskCount;
	protected float highTaskWorkLoad;
	protected boolean ischeck;
	protected long lowTaskCount;
	protected float lowTaskWorkLoad;
	protected long mediumTaskCount;
	protected float mediumTaskWorkLoad;
	protected String realname;
	protected long taskCount;
	protected float taskWorkLoad;

	/**
	 * Gets the value of the highTaskCount property.
	 * 
	 */
	public long getHighTaskCount() {
		return highTaskCount;
	}

	/**
	 * Sets the value of the highTaskCount property.
	 * 
	 */
	public void setHighTaskCount(long value) {
		this.highTaskCount = value;
	}

	/**
	 * Gets the value of the highTaskWorkLoad property.
	 * 
	 */
	public float getHighTaskWorkLoad() {
		return highTaskWorkLoad;
	}

	/**
	 * Sets the value of the highTaskWorkLoad property.
	 * 
	 */
	public void setHighTaskWorkLoad(float value) {
		this.highTaskWorkLoad = value;
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
	 * Gets the value of the lowTaskCount property.
	 * 
	 */
	public long getLowTaskCount() {
		return lowTaskCount;
	}

	/**
	 * Sets the value of the lowTaskCount property.
	 * 
	 */
	public void setLowTaskCount(long value) {
		this.lowTaskCount = value;
	}

	/**
	 * Gets the value of the lowTaskWorkLoad property.
	 * 
	 */
	public float getLowTaskWorkLoad() {
		return lowTaskWorkLoad;
	}

	/**
	 * Sets the value of the lowTaskWorkLoad property.
	 * 
	 */
	public void setLowTaskWorkLoad(float value) {
		this.lowTaskWorkLoad = value;
	}

	/**
	 * Gets the value of the mediumTaskCount property.
	 * 
	 */
	public long getMediumTaskCount() {
		return mediumTaskCount;
	}

	/**
	 * Sets the value of the mediumTaskCount property.
	 * 
	 */
	public void setMediumTaskCount(long value) {
		this.mediumTaskCount = value;
	}

	/**
	 * Gets the value of the mediumTaskWorkLoad property.
	 * 
	 */
	public float getMediumTaskWorkLoad() {
		return mediumTaskWorkLoad;
	}

	/**
	 * Sets the value of the mediumTaskWorkLoad property.
	 * 
	 */
	public void setMediumTaskWorkLoad(float value) {
		this.mediumTaskWorkLoad = value;
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
	 * Gets the value of the taskCount property.
	 * 
	 */
	public long getTaskCount() {
		return taskCount;
	}

	/**
	 * Sets the value of the taskCount property.
	 * 
	 */
	public void setTaskCount(long value) {
		this.taskCount = value;
	}

	/**
	 * Gets the value of the taskWorkLoad property.
	 * 
	 */
	public float getTaskWorkLoad() {
		return taskWorkLoad;
	}

	/**
	 * Sets the value of the taskWorkLoad property.
	 * 
	 */
	public void setTaskWorkLoad(float value) {
		this.taskWorkLoad = value;
	}

}
