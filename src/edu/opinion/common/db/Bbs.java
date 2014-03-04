package edu.opinion.common.db;

import java.util.Date;

/**
 * Bbs entity. @author MyEclipse Persistence Tools
 */

public class Bbs implements java.io.Serializable {

	// Fields

	private String id;
	private String url;
	private String hashcode;
	private String topic;
	private String author;
	private Date releasetime;
	private String renum;
	private String tag;
	private Date storagetime;
	private String idkey;
	private String content;

	// Constructors

	/** default constructor */
	public Bbs() {
	}

	/** full constructor */
	public Bbs(String url, String hashcode, String topic, String author,
			Date releasetime, String renum, String tag, Date storagetime,
			String idkey, String content) {
		this.url = url;
		this.hashcode = hashcode;
		this.topic = topic;
		this.author = author;
		this.releasetime = releasetime;
		this.renum = renum;
		this.tag = tag;
		this.storagetime = storagetime;
		this.idkey = idkey;
		this.content = content;
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

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getReleasetime() {
		return this.releasetime;
	}

	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}

	public String getRenum() {
		return this.renum;
	}

	public void setRenum(String renum) {
		this.renum = renum;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}