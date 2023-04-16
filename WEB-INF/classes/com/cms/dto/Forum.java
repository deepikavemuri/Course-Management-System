package com.cms.dto;

import com.mysql.cj.jdbc.Blob;

public class Forum {
	private int fid;
	private String msg;
	private Blob fileName;
	private int userId;
	private String FName;
	private String userType;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Blob getFileName() {
		return fileName;
	}
	public void setFileName(Blob fileName) {
		this.fileName = fileName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "Forum [fid=" + fid + ", msg=" + msg + ", fileName=" + fileName + ", userId=" + userId + ", FName="
				+ FName + ", userType=" + userType + "]";
	}
	public Forum(int fid, String msg, Blob fileName, int userId, String fName, String userType) {
		super();
		this.fid = fid;
		this.msg = msg;
		this.fileName = fileName;
		this.userId = userId;
		FName = fName;
		this.userType = userType;
	}
	public Forum() {

	}
	
	
	
}
