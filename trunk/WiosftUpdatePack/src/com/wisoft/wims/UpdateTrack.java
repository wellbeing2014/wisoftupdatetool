package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateTrack complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;updateTrack&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;arg0&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsSingleIssueTracking&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;arg1&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsStepInfo&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;arg2&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateTrack", propOrder = { "arg0", "arg1", "arg2" })
public class UpdateTrack {

	protected WimsSingleIssueTracking arg0;
	protected WimsStepInfo arg1;
	protected List<OmsPerson> arg2;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link WimsSingleIssueTracking }
	 * 
	 */
	public WimsSingleIssueTracking getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsSingleIssueTracking }
	 * 
	 */
	public void setArg0(WimsSingleIssueTracking value) {
		this.arg0 = value;
	}

	/**
	 * Gets the value of the arg1 property.
	 * 
	 * @return possible object is {@link WimsStepInfo }
	 * 
	 */
	public WimsStepInfo getArg1() {
		return arg1;
	}

	/**
	 * Sets the value of the arg1 property.
	 * 
	 * @param value
	 *            allowed object is {@link WimsStepInfo }
	 * 
	 */
	public void setArg1(WimsStepInfo value) {
		this.arg1 = value;
	}

	/**
	 * Gets the value of the arg2 property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the arg2 property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getArg2().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OmsPerson }
	 * 
	 * 
	 */
	public List<OmsPerson> getArg2() {
		if (arg2 == null) {
			arg2 = new ArrayList<OmsPerson>();
		}
		return this.arg2;
	}

}
