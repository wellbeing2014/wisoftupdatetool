package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for deleteTask complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;deleteTask&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;arg0&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsTaskmgr&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteTask", propOrder = { "arg0" })
public class DeleteTask {

	protected WimsTaskmgr arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link WimsTaskmgr }
	 * 
	 */
	public WimsTaskmgr getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsTaskmgr }
	 * 
	 */
	public void setArg0(WimsTaskmgr value) {
		this.arg0 = value;
	}

}
