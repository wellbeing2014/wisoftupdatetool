package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for proChangeServicesIn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;proChangeServicesIn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;kffzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;proName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;swfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;xmbh&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;xmfzr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;xmjd&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;xmzt&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proChangeServicesIn", propOrder = { "kffzr", "proName",
		"swfzr", "xmbh", "xmfzr", "xmjd", "xmzt" })
public class ProChangeServicesIn {

	protected boolean kffzr;
	protected boolean proName;
	protected boolean swfzr;
	protected boolean xmbh;
	protected boolean xmfzr;
	protected boolean xmjd;
	protected boolean xmzt;

	/**
	 * Gets the value of the kffzr property.
	 * 
	 */
	public boolean isKffzr() {
		return kffzr;
	}

	/**
	 * Sets the value of the kffzr property.
	 * 
	 */
	public void setKffzr(boolean value) {
		this.kffzr = value;
	}

	/**
	 * Gets the value of the proName property.
	 * 
	 */
	public boolean isProName() {
		return proName;
	}

	/**
	 * Sets the value of the proName property.
	 * 
	 */
	public void setProName(boolean value) {
		this.proName = value;
	}

	/**
	 * Gets the value of the swfzr property.
	 * 
	 */
	public boolean isSwfzr() {
		return swfzr;
	}

	/**
	 * Sets the value of the swfzr property.
	 * 
	 */
	public void setSwfzr(boolean value) {
		this.swfzr = value;
	}

	/**
	 * Gets the value of the xmbh property.
	 * 
	 */
	public boolean isXmbh() {
		return xmbh;
	}

	/**
	 * Sets the value of the xmbh property.
	 * 
	 */
	public void setXmbh(boolean value) {
		this.xmbh = value;
	}

	/**
	 * Gets the value of the xmfzr property.
	 * 
	 */
	public boolean isXmfzr() {
		return xmfzr;
	}

	/**
	 * Sets the value of the xmfzr property.
	 * 
	 */
	public void setXmfzr(boolean value) {
		this.xmfzr = value;
	}

	/**
	 * Gets the value of the xmjd property.
	 * 
	 */
	public boolean isXmjd() {
		return xmjd;
	}

	/**
	 * Sets the value of the xmjd property.
	 * 
	 */
	public void setXmjd(boolean value) {
		this.xmjd = value;
	}

	/**
	 * Gets the value of the xmzt property.
	 * 
	 */
	public boolean isXmzt() {
		return xmzt;
	}

	/**
	 * Sets the value of the xmzt property.
	 * 
	 */
	public void setXmzt(boolean value) {
		this.xmzt = value;
	}

}
