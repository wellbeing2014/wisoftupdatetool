package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for workLoadStatisticsByPro complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;workLoadStatisticsByPro&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;cehuaWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;ceshiWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;diaoyanWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;guanliWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;kaifaWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;newTotalByD&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;newTotalByH&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;oldTotal&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;proId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;proName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;qitaWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;shishiWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;totalByD&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;totalByM&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;weihuWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *         &lt;element name=&quot;zhichiWorkLoad&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workLoadStatisticsByPro", propOrder = { "cehuaWorkLoad",
		"ceshiWorkLoad", "diaoyanWorkLoad", "guanliWorkLoad", "kaifaWorkLoad",
		"newTotalByD", "newTotalByH", "oldTotal", "proId", "proName",
		"qitaWorkLoad", "shishiWorkLoad", "totalByD", "totalByM",
		"weihuWorkLoad", "zhichiWorkLoad" })
public class WorkLoadStatisticsByPro {

	protected double cehuaWorkLoad;
	protected double ceshiWorkLoad;
	protected double diaoyanWorkLoad;
	protected double guanliWorkLoad;
	protected double kaifaWorkLoad;
	protected double newTotalByD;
	protected double newTotalByH;
	protected double oldTotal;
	protected String proId;
	protected String proName;
	protected double qitaWorkLoad;
	protected double shishiWorkLoad;
	protected double totalByD;
	protected double totalByM;
	protected double weihuWorkLoad;
	protected double zhichiWorkLoad;

	/**
	 * Gets the value of the cehuaWorkLoad property.
	 * 
	 */
	public double getCehuaWorkLoad() {
		return cehuaWorkLoad;
	}

	/**
	 * Sets the value of the cehuaWorkLoad property.
	 * 
	 */
	public void setCehuaWorkLoad(double value) {
		this.cehuaWorkLoad = value;
	}

	/**
	 * Gets the value of the ceshiWorkLoad property.
	 * 
	 */
	public double getCeshiWorkLoad() {
		return ceshiWorkLoad;
	}

	/**
	 * Sets the value of the ceshiWorkLoad property.
	 * 
	 */
	public void setCeshiWorkLoad(double value) {
		this.ceshiWorkLoad = value;
	}

	/**
	 * Gets the value of the diaoyanWorkLoad property.
	 * 
	 */
	public double getDiaoyanWorkLoad() {
		return diaoyanWorkLoad;
	}

	/**
	 * Sets the value of the diaoyanWorkLoad property.
	 * 
	 */
	public void setDiaoyanWorkLoad(double value) {
		this.diaoyanWorkLoad = value;
	}

	/**
	 * Gets the value of the guanliWorkLoad property.
	 * 
	 */
	public double getGuanliWorkLoad() {
		return guanliWorkLoad;
	}

	/**
	 * Sets the value of the guanliWorkLoad property.
	 * 
	 */
	public void setGuanliWorkLoad(double value) {
		this.guanliWorkLoad = value;
	}

	/**
	 * Gets the value of the kaifaWorkLoad property.
	 * 
	 */
	public double getKaifaWorkLoad() {
		return kaifaWorkLoad;
	}

	/**
	 * Sets the value of the kaifaWorkLoad property.
	 * 
	 */
	public void setKaifaWorkLoad(double value) {
		this.kaifaWorkLoad = value;
	}

	/**
	 * Gets the value of the newTotalByD property.
	 * 
	 */
	public double getNewTotalByD() {
		return newTotalByD;
	}

	/**
	 * Sets the value of the newTotalByD property.
	 * 
	 */
	public void setNewTotalByD(double value) {
		this.newTotalByD = value;
	}

	/**
	 * Gets the value of the newTotalByH property.
	 * 
	 */
	public double getNewTotalByH() {
		return newTotalByH;
	}

	/**
	 * Sets the value of the newTotalByH property.
	 * 
	 */
	public void setNewTotalByH(double value) {
		this.newTotalByH = value;
	}

	/**
	 * Gets the value of the oldTotal property.
	 * 
	 */
	public double getOldTotal() {
		return oldTotal;
	}

	/**
	 * Sets the value of the oldTotal property.
	 * 
	 */
	public void setOldTotal(double value) {
		this.oldTotal = value;
	}

	/**
	 * Gets the value of the proId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProId() {
		return proId;
	}

	/**
	 * Sets the value of the proId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProId(String value) {
		this.proId = value;
	}

	/**
	 * Gets the value of the proName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * Sets the value of the proName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProName(String value) {
		this.proName = value;
	}

	/**
	 * Gets the value of the qitaWorkLoad property.
	 * 
	 */
	public double getQitaWorkLoad() {
		return qitaWorkLoad;
	}

	/**
	 * Sets the value of the qitaWorkLoad property.
	 * 
	 */
	public void setQitaWorkLoad(double value) {
		this.qitaWorkLoad = value;
	}

	/**
	 * Gets the value of the shishiWorkLoad property.
	 * 
	 */
	public double getShishiWorkLoad() {
		return shishiWorkLoad;
	}

	/**
	 * Sets the value of the shishiWorkLoad property.
	 * 
	 */
	public void setShishiWorkLoad(double value) {
		this.shishiWorkLoad = value;
	}

	/**
	 * Gets the value of the totalByD property.
	 * 
	 */
	public double getTotalByD() {
		return totalByD;
	}

	/**
	 * Sets the value of the totalByD property.
	 * 
	 */
	public void setTotalByD(double value) {
		this.totalByD = value;
	}

	/**
	 * Gets the value of the totalByM property.
	 * 
	 */
	public double getTotalByM() {
		return totalByM;
	}

	/**
	 * Sets the value of the totalByM property.
	 * 
	 */
	public void setTotalByM(double value) {
		this.totalByM = value;
	}

	/**
	 * Gets the value of the weihuWorkLoad property.
	 * 
	 */
	public double getWeihuWorkLoad() {
		return weihuWorkLoad;
	}

	/**
	 * Sets the value of the weihuWorkLoad property.
	 * 
	 */
	public void setWeihuWorkLoad(double value) {
		this.weihuWorkLoad = value;
	}

	/**
	 * Gets the value of the zhichiWorkLoad property.
	 * 
	 */
	public double getZhichiWorkLoad() {
		return zhichiWorkLoad;
	}

	/**
	 * Sets the value of the zhichiWorkLoad property.
	 * 
	 */
	public void setZhichiWorkLoad(double value) {
		this.zhichiWorkLoad = value;
	}

}
