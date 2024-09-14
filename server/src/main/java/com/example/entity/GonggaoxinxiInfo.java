package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "gonggaoxinxi_info")
public class GonggaoxinxiInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gonggaobiaoti")	private String gonggaobiaoti;	@Column(name = "gonggaoneirong")	private String gonggaoneirong;	
	@Column(name = "addtime")
	private String addtime;
	@Column(name = "status")
	private String status;
	//yoxuxtupTransiexnt
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	//gextsexttpzidxuan

	//public String getFileIds() {
//		return fileIds;
//	}
//
//	public void setFileIds(String fileIds) {
//		this.fileIds = fileIds;
//	}

	
	public String getGonggaobiaoti() {
        return gonggaobiaoti;
    }
    public void setGonggaobiaoti(String gonggaobiaoti) {
		this.gonggaobiaoti = gonggaobiaoti;
    }	public String getGonggaoneirong() {
        return gonggaoneirong;
    }
    public void setGonggaoneirong(String gonggaoneirong) {
		this.gonggaoneirong = gonggaoneirong;
    }	

	public String getAddtime() {
        return addtime;
    }
    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

}