package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "liuyanban_info")
public class LiuyanbanInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "yonghuming")
	private String yonghuming;
	@Column(name = "cheng")
	private String cheng;
	@Column(name = "touxiang")
	private String touxiang;
	@Column(name = "biaoti")
	private String biaoti;
	@Column(name = "neirong")
	private String neirong;
	@Column(name = "huifu")
	private String huifu;
	
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

	
	public String getYonghuming() {
        return yonghuming;
    }
    public void setYonghuming(String yonghuming) {
		this.yonghuming = yonghuming;
    }
	public String getCheng() {
        return cheng;
    }
    public void setCheng(String cheng) {
		this.cheng = cheng;
    }
	public String getTouxiang() {
        return touxiang;
    }
    public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
    }
	public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
    }
	public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
		this.neirong = neirong;
    }
	public String getHuifu() {
        return huifu;
    }
    public void setHuifu(String huifu) {
		this.huifu = huifu;
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