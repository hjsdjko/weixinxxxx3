package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "xinwentongzhi_info")
public class XinwentongzhiInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "biaoti")	private String biaoti;	@Column(name = "leibie")	private String leibie;	@Column(name = "neirong")	private String neirong;	@Column(name = "shouyetupian")	private String shouyetupian;	@Column(name = "zhaiyao")	private String zhaiyao;	@Column(name = "dianjilv")	private String dianjilv;	@Column(name = "faburen")	private String faburen;	@Column(name = "dianzan_d")	private String dianzan_d;	@Column(name = "dianzan_c")	private String dianzan_c;	
	@Column(name = "addtime")
	private String addtime;
	@Column(name = "status")
	private String status;
	@Transient private List<Long> shouyetupianflst;	//yoxuxtupTransiexnt
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long>  getShouyetupianflst() {
        return shouyetupianflst;
    }
    public void setShouyetupianflst(List<Long> shouyetupianflst) {
		this.shouyetupianflst = shouyetupianflst;
    }

	//public String getFileIds() {
//		return fileIds;
//	}
//
//	public void setFileIds(String fileIds) {
//		this.fileIds = fileIds;
//	}

	
	public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
    }	public String getLeibie() {
        return leibie;
    }
    public void setLeibie(String leibie) {
		this.leibie = leibie;
    }	public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
		this.neirong = neirong;
    }	public String getShouyetupian() {
        return shouyetupian;
    }
    public void setShouyetupian(String shouyetupian) {
		this.shouyetupian = shouyetupian;
    }	public String getZhaiyao() {
        return zhaiyao;
    }
    public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
    }	public String getDianjilv() {
        return dianjilv;
    }
    public void setDianjilv(String dianjilv) {
		this.dianjilv = dianjilv;
    }	public String getFaburen() {
        return faburen;
    }
    public void setFaburen(String faburen) {
		this.faburen = faburen;
    }	public String getDianzan_d() {
        return dianzan_d;
    }
    public void setDianzan_d(String dianzan_d) {
		this.dianzan_d = dianzan_d;
    }	public String getDianzan_c() {
        return dianzan_c;
    }
    public void setDianzan_c(String dianzan_c) {
		this.dianzan_c = dianzan_c;
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