package edu.opinion.common.db;

import java.util.Date;

/**
 * Rebbs entity. @author MyEclipse Persistence Tools
 */

public class Rebbs implements java.io.Serializable {

	// Fields

	private String id;
	private String url;
	private String hashcode;
	private String retopic;
	private String reauthor;
	private Date retime;
	private String tag;
	private Date storagetime;
	private String idkey;
	private String recontent;

	// Constructors

	/** default constructor */
	public Rebbs() {
	}

	/** full constructor */
	public Rebbs(String url, String hashcode, String retopic, String reauthor,
			Date retime, String tag, Date storagetime, String idkey,
			String recontent) {
		this.url = url;
		this.hashcode = hashcode;
		this.retopic = retopic;
		this.reauthor = reauthor;
		this.retime = retime;
		this.tag = tag;
		this.storagetime = storagetime;
		this.idkey = idkey;
		this.recontent = recontent;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHashcode() {
		return this.hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public String getRetopic() {
		return this.retopic;
	}

	public void setRetopic(String retopic) {
		this.retopic = retopic;
	}

	public String getReauthor() {
		return this.reauthor;
	}

	public void setReauthor(String reauthor) {
		this.reauthor = reauthor;
	}

	public Date getRetime() {
		return this.retime;
	}

	public void setRetime(Date retime) {
		this.retime = retime;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getStoragetime() {
		return this.storagetime;
	}

	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}

	public String getIdkey() {
		return this.idkey;
	}

	public void setIdkey(String idkey) {
		this.idkey = idkey;
	}

	public String getRecontent() {
		return this.recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

}