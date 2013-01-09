package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for taskReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;taskReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;value&quot; type=&quot;{http://www.springframework.org/schema/beans}wimsTaskmgr&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskReturn", propOrder = { "value" })
public class TaskReturn extends BaseReturn {

	@XmlElement(nillable = true)
	protected List<WimsTaskmgr> value;

	/**
	 * Gets the value of the value property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the value property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getValue().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link WimsTaskmgr }
	 * 
	 * 
	 */
	public List<WimsTaskmgr> getValue() {
		if (value == null) {
			value = new ArrayList<WimsTaskmgr>();
		}
		return this.value;
	}

}
