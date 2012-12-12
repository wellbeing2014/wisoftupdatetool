package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for personGroup complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;personGroup&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;allCheck&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;checkedCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;groupIndex&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;groupName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;personCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;personList&quot; type=&quot;{http://www.springframework.org/schema/beans}ownerServicesIn&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personGroup", propOrder = { "allCheck", "checkedCount",
		"groupIndex", "groupName", "personCount", "personList" })
public class PersonGroup {

	protected boolean allCheck;
	protected int checkedCount;
	protected int groupIndex;
	protected String groupName;
	protected int personCount;
	@XmlElement(nillable = true)
	protected List<OwnerServicesIn> personList;

	/**
	 * Gets the value of the allCheck property.
	 * 
	 */
	public boolean isAllCheck() {
		return allCheck;
	}

	/**
	 * Sets the value of the allCheck property.
	 * 
	 */
	public void setAllCheck(boolean value) {
		this.allCheck = value;
	}

	/**
	 * Gets the value of the checkedCount property.
	 * 
	 */
	public int getCheckedCount() {
		return checkedCount;
	}

	/**
	 * Sets the value of the checkedCount property.
	 * 
	 */
	public void setCheckedCount(int value) {
		this.checkedCount = value;
	}

	/**
	 * Gets the value of the groupIndex property.
	 * 
	 */
	public int getGroupIndex() {
		return groupIndex;
	}

	/**
	 * Sets the value of the groupIndex property.
	 * 
	 */
	public void setGroupIndex(int value) {
		this.groupIndex = value;
	}

	/**
	 * Gets the value of the groupName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the value of the groupName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGroupName(String value) {
		this.groupName = value;
	}

	/**
	 * Gets the value of the personCount property.
	 * 
	 */
	public int getPersonCount() {
		return personCount;
	}

	/**
	 * Sets the value of the personCount property.
	 * 
	 */
	public void setPersonCount(int value) {
		this.personCount = value;
	}

	/**
	 * Gets the value of the personList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the personList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPersonList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OwnerServicesIn }
	 * 
	 * 
	 */
	public List<OwnerServicesIn> getPersonList() {
		if (personList == null) {
			personList = new ArrayList<OwnerServicesIn>();
		}
		return this.personList;
	}

}
