package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findTaskPersonInfoByTrackIdResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;findTaskPersonInfoByTrackIdResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;return&quot; type=&quot;{http://www.springframework.org/schema/beans}personListByTaskAndTrackIdReturn&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findTaskPersonInfoByTrackIdResponse", propOrder = { "_return" })
public class FindTaskPersonInfoByTrackIdResponse {

	@XmlElement(name = "return")
	protected PersonListByTaskAndTrackIdReturn _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link PersonListByTaskAndTrackIdReturn }
	 * 
	 */
	public PersonListByTaskAndTrackIdReturn getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link PersonListByTaskAndTrackIdReturn }
	 * 
	 */
	public void setReturn(PersonListByTaskAndTrackIdReturn value) {
		this._return = value;
	}

}
