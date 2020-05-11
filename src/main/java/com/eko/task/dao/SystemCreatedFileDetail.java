package com.eko.task.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemcreatedfiledetail")
public class SystemCreatedFileDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fileid")
	private int fileId;
	
	private String fileName;

	public int getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	

}
