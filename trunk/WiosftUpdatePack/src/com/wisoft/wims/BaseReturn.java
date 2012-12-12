package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for baseReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;baseReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;isSuccued&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;message&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseReturn", propOrder = { "isSuccued", "message" })
public class BaseReturn {

	protected boolean isSuccued;
	protected String message;

	/**
	 * Gets the value of the isSuccued property.
	 * 
	 */
	public boolean isIsSuccued() {
		return isSuccued;
	}

	/**
	 * Sets the value of the isSuccued property.
	 * 
	 */
	public void setIsSuccued(boolean value) {
		this.isSuccued = value;
	}

	/**
	 * Gets the value of the message property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the value of the message property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMessage(String value) {
		this.message = value;
	}

}
