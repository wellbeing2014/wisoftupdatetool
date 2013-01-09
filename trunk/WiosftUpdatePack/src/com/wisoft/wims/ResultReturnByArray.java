package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for resultReturnByArray complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;resultReturnByArray&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;arrayobj&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;counts&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;pageCount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;pageno&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultReturnByArray", propOrder = { "arrayobj", "counts",
		"pageCount", "pageno" })
public class ResultReturnByArray extends BaseReturn {

	@XmlElement(nillable = true)
	protected List<Object> arrayobj;
	protected int counts;
	protected int pageCount;
	protected int pageno;

	/**
	 * Gets the value of the arrayobj property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the arrayobj property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getArrayobj().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * 
	 * 
	 */
	public List<Object> getArrayobj() {
		if (arrayobj == null) {
			arrayobj = new ArrayList<Object>();
		}
		return this.arrayobj;
	}

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
	 * Gets the value of the pageCount property.
	 * 
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Sets the value of the pageCount property.
	 * 
	 */
	public void setPageCount(int value) {
		this.pageCount = value;
	}

	/**
	 * Gets the value of the pageno property.
	 * 
	 */
	public int getPageno() {
		return pageno;
	}

	/**
	 * Sets the value of the pageno property.
	 * 
	 */
	public void setPageno(int value) {
		this.pageno = value;
	}

}
