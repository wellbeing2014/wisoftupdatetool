package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findTaskWorkLoadStatisticsByProResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;findTaskWorkLoadStatisticsByProResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;return&quot; type=&quot;{http://www.springframework.org/schema/beans}workLoadStatisticsByProListReturn&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findTaskWorkLoadStatisticsByProResponse", propOrder = { "_return" })
public class FindTaskWorkLoadStatisticsByProResponse {

	@XmlElement(name = "return")
	protected WorkLoadStatisticsByProListReturn _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link WorkLoadStatisticsByProListReturn }
	 * 
	 */
	public WorkLoadStatisticsByProListReturn getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link WorkLoadStatisticsByProListReturn }
	 * 
	 */
	public void setReturn(WorkLoadStatisticsByProListReturn value) {
		this._return = value;
	}

}
