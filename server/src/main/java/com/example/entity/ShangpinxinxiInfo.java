package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "shangpinxinxi_info")
public class ShangpinxinxiInfo extends Account {
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
	@Column(name = "tupian")
	private String tupian;
	@Column(name = "beizhu")
	private String beizhu;
	
	@Column(name = "addtime")
	private String addtime;
	@Column(name = "status")
	private String status;


	@Transient
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}




	@Transient private List<Long> tupianflst;	//yoxuxtupTransiexnt
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long>  getTupianflst() {
        return tupianflst;
    }
    public void setTupianflst(List<Long> tupianflst) {
		this.tupianflst = tupianflst;
    }

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
	public String getTupian() {
        return tupian;
    }
    public void setTupian(String tupian) {
		this.tupian = tupian;
    }
	public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
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