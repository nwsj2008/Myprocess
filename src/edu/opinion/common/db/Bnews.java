package edu.opinion.common.db;

import java.util.Date;

/**
 * Bnews entity. @author MyEclipse Persistence Tools
 */

public class Bnews implements java.io.Serializable {

	// Fields

	private String id;
	private String url;
	private String hashcode;
	private String topic;
	private String tag;
	private String source;
	private Date releasetime;
	private Date storagetime;
	private String content;

	// Constructors

	/** default constructor */
	public Bnews() {
	}

	/** minimal constructor */
	public Bnews(String url, String hashcode, String topic, String tag,
			String source, Date releasetime, Date storagetime) {
		this.url = url;
		this.hashcode = hashcode;
		this.topic = topic;
		this.tag = tag;
		this.source = source;
		this.releasetime = releasetime;
		this.storagetime = storagetime;
	}

	/** full constructor */
	public Bnews(String url, String hashcode, String topic, String tag,
			String source, Date releasetime, Date storagetime, String content) {
		this.url = url;
		this.hashcode = hashcode;
		this.topic = topic;
		this.tag = tag;
		this.source = source;
		this.releasetime = releasetime;
		this.storagetime = storagetime;
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

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getReleasetime() {
		return this.releasetime;
	}

	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}

	public Date getStoragetime() {
		return this.storagetime;
	}

	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}