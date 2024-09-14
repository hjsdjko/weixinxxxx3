package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "goumai_info")
public class GoumaiInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "shangpinbianhao")
	private String shangpinbianhao;
	@Column(name = "shangpinmingcheng")
	private String shangpinmingcheng;
	@Column(name = "shangpinleibie")
	private String shangpinleibie;
	@Column(name = "jiage")
	private String jiage;
	@Column(name = "kucun")
	private String kucun;
	@Column(name = "xiaoliang")
	private String xiaoliang;
	@Column(name = "goumaishuliang")
	private String goumaishuliang;
	@Column(name = "goumaijine")
	private String goumaijine;
	@Column(name = "goumairen")
	private Long goumairen;
	@Column(name = "addtime")
	private String addtime;
	@Column(name = "status")
	private String status;
	private String dianhua;
	private String dizhi;

	@Transient
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

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
    }
	public String getShangpinmingcheng() {
        return shangpinmingcheng;
    }
    public void setShangpinmingcheng(String shangpinmingcheng) {
		this.shangpinmingcheng = shangpinmingcheng;
    }
	public String getShangpinleibie() {
        return shangpinleibie;
    }
    public void setShangpinleibie(String shangpinleibie) {
		this.shangpinleibie = shangpinleibie;
    }
	public String getJiage() {
        return jiage;
    }
    public void setJiage(String jiage) {
		this.jiage = jiage;
    }
	public String getKucun() {
        return kucun;
    }
    public void setKucun(String kucun) {
		this.kucun = kucun;
    }
	public String getXiaoliang() {
        return xiaoliang;
    }
    public void setXiaoliang(String xiaoliang) {
		this.xiaoliang = xiaoliang;
    }
	public String getGoumaishuliang() {
        return goumaishuliang;
    }
    public void setGoumaishuliang(String goumaishuliang) {
		this.goumaishuliang = goumaishuliang;
    }
	public String getGoumaijine() {
        return goumaijine;
    }
    public void setGoumaijine(String goumaijine) {
		this.goumaijine = goumaijine;
    }
	public Long getGoumairen() {
        return goumairen;
    }
    public void setGoumairen(Long goumairen) {
		this.goumairen = goumairen;
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
