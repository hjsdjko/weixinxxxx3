package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "nongchanpinpinglun_info")
public class NongchanpinpinglunInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "shangpinbianhao")	private String shangpinbianhao;	@Column(name = "shangpinmingcheng")	private String shangpinmingcheng;	@Column(name = "pingfen")	private String pingfen;	@Column(name = "pingjia")	private String pingjia;	@Column(name = "zhanghao")	private String zhanghao;	@Column(name = "xingming")	private String xingming;	
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

	
	public String getShangpinbianhao() {
        return shangpinbianhao;
    }
    public void setShangpinbianhao(String shangpinbianhao) {
		this.shangpinbianhao = shangpinbianhao;
    }	public String getShangpinmingcheng() {
        return shangpinmingcheng;
    }
    public void setShangpinmingcheng(String shangpinmingcheng) {
		this.shangpinmingcheng = shangpinmingcheng;
    }	public String getPingfen() {
        return pingfen;
    }
    public void setPingfen(String pingfen) {
		this.pingfen = pingfen;
    }	public String getPingjia() {
        return pingjia;
    }
    public void setPingjia(String pingjia) {
		this.pingjia = pingjia;
    }	public String getZhanghao() {
        return zhanghao;
    }
    public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
    }	public String getXingming() {
        return xingming;
    }
    public void setXingming(String xingming) {
		this.xingming = xingming;
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