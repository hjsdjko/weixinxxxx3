package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "xiaoyuantongzhi_info")
public class XiaoyuantongzhiInfo extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tongzhibianhao")
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

	
	public String getTongzhibianhao() {
        return tongzhibianhao;
    }
    public void setTongzhibianhao(String tongzhibianhao) {
		this.tongzhibianhao = tongzhibianhao;
    }
        return tongzhibiaoti;
    }
    public void setTongzhibiaoti(String tongzhibiaoti) {
		this.tongzhibiaoti = tongzhibiaoti;
    }
        return tongzhileixing;
    }
    public void setTongzhileixing(String tongzhileixing) {
		this.tongzhileixing = tongzhileixing;
    }
        return tongzhineirong;
    }
    public void setTongzhineirong(String tongzhineirong) {
		this.tongzhineirong = tongzhineirong;
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