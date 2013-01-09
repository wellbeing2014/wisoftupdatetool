package com.wisoft.wims;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for workLoadStatisticsByProListReturn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;workLoadStatisticsByProListReturn&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://www.springframework.org/schema/beans}baseReturn&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;benqiheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;cehuaheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;ceshiheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;diaoyanheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;guanliheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;kaifaheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;qitaheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;shangqiheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;shishiheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;weihuheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;workLoadArray&quot; type=&quot;{http://www.springframework.org/schema/beans}workLoadStatisticsByPro&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;zhichiheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;zongheji&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workLoadStatisticsByProListReturn", propOrder = { "benqiheji",
		"cehuaheji", "ceshiheji", "diaoyanheji", "guanliheji", "kaifaheji",
		"qitaheji", "shangqiheji", "shishiheji", "weihuheji", "workLoadArray",
		"zhichiheji", "zongheji" })
public class WorkLoadStatisticsByProListReturn extends BaseReturn {

	protected double benqiheji;
	protected double cehuaheji;
	protected double ceshiheji;
	protected double diaoyanheji;
	protected double guanliheji;
	protected double kaifaheji;
	protected double qitaheji;
	protected double shangqiheji;
	protected double shishiheji;
	protected double weihuheji;
	@XmlElement(nillable = true)
	protected List<WorkLoadStatisticsByPro> workLoadArray;
	protected double zhichiheji;
	protected double zongheji;

	/**
	 * Gets the value of the benqiheji property.
	 * 
	 */
	public double getBenqiheji() {
		return benqiheji;
	}

	/**
	 * Sets the value of the benqiheji property.
	 * 
	 */
	public void setBenqiheji(double value) {
		this.benqiheji = value;
	}

	/**
	 * Gets the value of the cehuaheji property.
	 * 
	 */
	public double getCehuaheji() {
		return cehuaheji;
	}

	/**
	 * Sets the value of the cehuaheji property.
	 * 
	 */
	public void setCehuaheji(double value) {
		this.cehuaheji = value;
	}

	/**
	 * Gets the value of the ceshiheji property.
	 * 
	 */
	public double getCeshiheji() {
		return ceshiheji;
	}

	/**
	 * Sets the value of the ceshiheji property.
	 * 
	 */
	public void setCeshiheji(double value) {
		this.ceshiheji = value;
	}

	/**
	 * Gets the value of the diaoyanheji property.
	 * 
	 */
	public double getDiaoyanheji() {
		return diaoyanheji;
	}

	/**
	 * Sets the value of the diaoyanheji property.
	 * 
	 */
	public void setDiaoyanheji(double value) {
		this.diaoyanheji = value;
	}

	/**
	 * Gets the value of the guanliheji property.
	 * 
	 */
	public double getGuanliheji() {
		return guanliheji;
	}

	/**
	 * Sets the value of the guanliheji property.
	 * 
	 */
	public void setGuanliheji(double value) {
		this.guanliheji = value;
	}

	/**
	 * Gets the value of the kaifaheji property.
	 * 
	 */
	public double getKaifaheji() {
		return kaifaheji;
	}

	/**
	 * Sets the value of the kaifaheji property.
	 * 
	 */
	public void setKaifaheji(double value) {
		this.kaifaheji = value;
	}

	/**
	 * Gets the value of the qitaheji property.
	 * 
	 */
	public double getQitaheji() {
		return qitaheji;
	}

	/**
	 * Sets the value of the qitaheji property.
	 * 
	 */
	public void setQitaheji(double value) {
		this.qitaheji = value;
	}

	/**
	 * Gets the value of the shangqiheji property.
	 * 
	 */
	public double getShangqiheji() {
		return shangqiheji;
	}

	/**
	 * Sets the value of the shangqiheji property.
	 * 
	 */
	public void setShangqiheji(double value) {
		this.shangqiheji = value;
	}

	/**
	 * Gets the value of the shishiheji property.
	 * 
	 */
	public double getShishiheji() {
		return shishiheji;
	}

	/**
	 * Sets the value of the shishiheji property.
	 * 
	 */
	public void setShishiheji(double value) {
		this.shishiheji = value;
	}

	/**
	 * Gets the value of the weihuheji property.
	 * 
	 */
	public double getWeihuheji() {
		return weihuheji;
	}

	/**
	 * Sets the value of the weihuheji property.
	 * 
	 */
	public void setWeihuheji(double value) {
		this.weihuheji = value;
	}

	/**
	 * Gets the value of the workLoadArray property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the workLoadArray property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getWorkLoadArray().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link WorkLoadStatisticsByPro }
	 * 
	 * 
	 */
	public List<WorkLoadStatisticsByPro> getWorkLoadArray() {
		if (workLoadArray == null) {
			workLoadArray = new ArrayList<WorkLoadStatisticsByPro>();
		}
		return this.workLoadArray;
	}

	/**
	 * Gets the value of the zhichiheji property.
	 * 
	 */
	public double getZhichiheji() {
		return zhichiheji;
	}

	/**
	 * Sets the value of the zhichiheji property.
	 * 
	 */
	public void setZhichiheji(double value) {
		this.zhichiheji = value;
	}

	/**
	 * Gets the value of the zongheji property.
	 * 
	 */
	public double getZongheji() {
		return zongheji;
	}

	/**
	 * Sets the value of the zongheji property.
	 * 
	 */
	public void setZongheji(double value) {
		this.zongheji = value;
	}

}
