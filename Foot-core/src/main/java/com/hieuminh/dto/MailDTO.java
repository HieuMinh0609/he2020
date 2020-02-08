package com.hieuminh.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 1254874852012509478L;

	private String mailFrom;
	private String[] mailTo;
	private String[] mailCc;
	private String[] mailBcc;
	private Map<String,Object> model;
	private String template;

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public Date getMailSendDate() {
		return new Date();
	}

	public Map <String, Object> getModel() {
		return model;
	}

	public void setModel(Map <String, Object> model) {
		this.model = model;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String[] getMailTo() {
		return mailTo;
	}

	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}

	public String[] getMailCc() {
		return mailCc;
	}

	public void setMailCc(String[] mailCc) {
		this.mailCc = mailCc;
	}

	public String[] getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String[] mailBcc) {
		this.mailBcc = mailBcc;
	}
}
