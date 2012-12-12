package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for personArrayReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;personArrayReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;omsPerson&quot; type=&quot;{http://www.springframework.org/schema/beans}omsPerson&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;teamid&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personArrayReturn", propOrder = { "omsPerson", "teamid" })
public class PersonArrayReturn extends BaseReturn {

	@XmlElement(nillable = true)
	protected List<OmsPerson> omsPerson;
	protected String teamid;

	/**
	 * Gets the value of the omsPerson property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the omsPerson property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOmsPerson().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link OmsPerson }
	 * 
	 * 
	 */
	public List<OmsPerson> getOmsPerson() {
		if (omsPerson == null) {
			omsPerson = new ArrayList<OmsPerson>();
		}
		return this.omsPerson;
	}

	/**
	 * Gets the value of the teamid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTeamid() {
		return teamid;
	}

	/**
	 * Sets the value of the teamid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTeamid(String value) {
		this.teamid = value;
	}

}
