package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "pinkunrenzhengshenqing_info")
public class PinkunrenzhengshenqingInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "shenqingbiaoti")
	@Column(name = "addtime")
	private String addtime;
	@Column(name = "status")
	private String status;
	@Transient private List<Long> pinkunzhengshuflst;	//yoxuxtupTransiexnt
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long>  getPinkunzhengshuflst() {
        return pinkunzhengshuflst;
    }
    public void setPinkunzhengshuflst(List<Long> pinkunzhengshuflst) {
		this.pinkunzhengshuflst = pinkunzhengshuflst;
    }

	//public String getFileIds() {
//		return fileIds;
//	}
//
//	public void setFileIds(String fileIds) {
//		this.fileIds = fileIds;
//	}

	
	public String getShenqingbiaoti() {
        return shenqingbiaoti;
    }
    public void setShenqingbiaoti(String shenqingbiaoti) {
		this.shenqingbiaoti = shenqingbiaoti;
    }
        return pinkunzhengshu;
    }
    public void setPinkunzhengshu(String pinkunzhengshu) {
		this.pinkunzhengshu = pinkunzhengshu;
    }
        return shenqingshuoming;
    }
    public void setShenqingshuoming(String shenqingshuoming) {
		this.shenqingshuoming = shenqingshuoming;
    }
        return zhanghao;
    }
    public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
    }
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