package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for resultPersonListByProInfoReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;resultPersonListByProInfoReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;kffzrList&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;swfzrList&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;xmfzrList&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultPersonListByProInfoReturn", propOrder = { "kffzrList",
		"swfzrList", "xmfzrList" })
public class ResultPersonListByProInfoReturn extends BaseReturn {

	@XmlElement(nillable = true)
	protected List<OmsPerson> kffzrList;
	@XmlElement(nillable = true)
	protected List<OmsPerson> swfzrList;
	@XmlElement(nillable = true)
	protected List<OmsPerson> xmfzrList;

	/**
	 * Gets the value of the kffzrList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the kffzrList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getKffzrList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OmsPerson }
	 * 
	 * 
	 */
	public List<OmsPerson> getKffzrList() {
		if (kffzrList == null) {
			kffzrList = new ArrayList<OmsPerson>();
		}
		return this.kffzrList;
	}

	/**
	 * Gets the value of the swfzrList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the swfzrList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSwfzrList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OmsPerson }
	 * 
	 * 
	 */
	public List<OmsPerson> getSwfzrList() {
		if (swfzrList == null) {
			swfzrList = new ArrayList<OmsPerson>();
		}
		return this.swfzrList;
	}

	/**
	 * Gets the value of the xmfzrList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the xmfzrList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getXmfzrList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OmsPerson }
	 * 
	 * 
	 */
	public List<OmsPerson> getXmfzrList() {
		if (xmfzrList == null) {
			xmfzrList = new ArrayList<OmsPerson>();
		}
		return this.xmfzrList;
	}

}
