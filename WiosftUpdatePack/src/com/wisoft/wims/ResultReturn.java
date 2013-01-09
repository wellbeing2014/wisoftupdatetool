package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for resultReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;resultReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;counts&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;lst&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultReturn", propOrder = { "counts", "lst" })
public class ResultReturn extends BaseReturn {

	protected int counts;
	@XmlElement(nillable = true)
	protected List<Object> lst;

	/**
	 * Gets the value of the counts property.
	 * 
	 */
	public int getCounts() {
		return counts;
	}

	/**
	 * Sets the value of the counts property.
	 * 
	 */
	public void setCounts(int value) {
		this.counts = value;
	}

	/**
	 * Gets the value of the lst property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the lst property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getLst().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * 
	 * 
	 */
	public List<Object> getLst() {
		if (lst == null) {
			lst = new ArrayList<Object>();
		}
		return this.lst;
	}

}
