package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "user_text_info")
public class UserTextInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;
	@Column(name = "userId")
	private Long userId;
	@Column(name = "level")
	private String level;
	/**
	 * imageid
	 */
	@Column(name = "fileIds")
	private String fileIds;
	@Transient
	private List<Long> fileList;
	private String description;
	private Integer click;
	private String status;
	private String reason;


	private String fenxiang;

	public String getFenxiang() {
		return fenxiang;
	}

	public void setFenxiang(String fenxiang) {
		this.fenxiang = fenxiang;
	}

	@Transient
	private String xuqiu;
	@Transient
	private String userName;
	@Transient
	private String avater;
	@Transient
	private Integer commentCount;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getXuqiu() {
		return xuqiu;
	}

	public void setXuqiu(String xuqiu) {
		this.xuqiu = xuqiu;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvater() {
		return avater;
	}

	public void setAvater(String avater) {
		this.avater = avater;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public List<Long> getFileList() {
		return fileList;
	}

	public void setFileList(List<Long> fileList) {
		this.fileList = fileList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
