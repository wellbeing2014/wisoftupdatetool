package com.wisoft.wims;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for nfmFileinf complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;nfmFileinf&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;filename&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;filesize&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;isdelete&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;nfmFileamountId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;uploadtime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nfmFileinf", propOrder = { "filename", "filesize", "id",
		"isdelete", "nfmFileamountId", "uploadtime" })
public class NfmFileinf {

	protected String filename;
	protected String filesize;
	protected String id;
	protected String isdelete;
	protected String nfmFileamountId;
	protected String uploadtime;

	/**
	 * Gets the value of the filename property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the value of the filename property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFilename(String value) {
		this.filename = value;
	}

	/**
	 * Gets the value of the filesize property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * Sets the value of the filesize property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFilesize(String value) {
		this.filesize = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the isdelete property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsdelete() {
		return isdelete;
	}

	/**
	 * Sets the value of the isdelete property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsdelete(String value) {
		this.isdelete = value;
	}

	/**
	 * Gets the value of the nfmFileamountId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNfmFileamountId() {
		return nfmFileamountId;
	}

	/**
	 * Sets the value of the nfmFileamountId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNfmFileamountId(String value) {
		this.nfmFileamountId = value;
	}

	/**
	 * Gets the value of the uploadtime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUploadtime() {
		return uploadtime;
	}

	/**
	 * Sets the value of the uploadtime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUploadtime(String value) {
		this.uploadtime = value;
	}

}
