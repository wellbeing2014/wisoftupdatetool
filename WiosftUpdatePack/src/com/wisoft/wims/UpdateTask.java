package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateTask complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;updateTask&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;arg0&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;arg1&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsTaskmgr&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;arg2&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsTaskmgr&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateTask", propOrder = { "arg0", "arg1", "arg2" })
public class UpdateTask {

	protected OmsPerson arg0;
	protected WimsTaskmgr arg1;
	protected WimsTaskmgr arg2;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link OmsPerson }
	 * 
	 */
	public OmsPerson getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link OmsPerson }
	 * 
	 */
	public void setArg0(OmsPerson value) {
		this.arg0 = value;
	}

	/**
	 * Gets the value of the arg1 property.
	 * 
	 * @return possible object is {@link WimsTaskmgr }
	 * 
	 */
	public WimsTaskmgr getArg1() {
		return arg1;
	}

	/**
	 * Sets the value of the arg1 property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsTaskmgr }
	 * 
	 */
	public void setArg1(WimsTaskmgr value) {
		this.arg1 = value;
	}

	/**
	 * Gets the value of the arg2 property.
	 * 
	 * @return possible object is {@link WimsTaskmgr }
	 * 
	 */
	public WimsTaskmgr getArg2() {
		return arg2;
	}

	/**
	 * Sets the value of the arg2 property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsTaskmgr }
	 * 
	 */
	public void setArg2(WimsTaskmgr value) {
		this.arg2 = value;
	}

}
