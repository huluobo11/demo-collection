package com.qqmail;

/**
 * @date 2017年8月16日
 * @author Administrator
 * @project demo
 */
public class MailEntity {
	// 要发送用户的邮箱
	private String to;
	// 发送内容
	private String messageText;
	// 发送的标题
	private String subject;

	public MailEntity(String to, String subject, String messageText) {
		this.to = to;
		this.messageText = messageText;
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
