package com.example.entity;

import javax.persistence.*;

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private String username;
   // @Transient
//    private String gonghao;
	@Transientprivate String zhanghao;
//    @Column(name = "xingming")
//    private String xingming;
//    @Column(name = "mima")
//    private String mima;
    @Transient
    private String mima;
    @Transient
    private String level;
  //  @Column(name = "sex")
  //  private String sex;
    @Transient
    private String newMima;
    @Transient
    private String address;
    //  @Column(name = "nickName")
    //  private String nickName;
    @Transient
    private String status;
    //   @Transient
    //  private String phone;



    @Transient
    private Double account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   // public String getGonghao() {
//        return gonghao;
//    }
//
//    public void setGonghao(String gonghao) {
//        this.gonghao = gonghao;
//    }

//    public String getXingming() {
//        return xingming;
//    }
//
//    public void setXingming(String xingming) {
//        this.xingming = xingming;
//    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

//    public String getMima() {
//        return mima;
//    }
//
//    public void setMima(String mima) {
//        this.mima = mima;
//    }
public String getZhanghao() {
        return zhanghao;
    }
    public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    //public String getSex() {
    //    return sex;
    //  }

    //  public void setSex(String sex) {
    //     this.sex = sex;
    //  }

    public String getNewMima() {
        return newMima;
    }

    public void setNewMima(String newMima) {
        this.newMima = newMima;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //   public String getNickName() {
    //     return nickName;
    // }

    //  public void setNickName(String nickName) {
    //      this.nickName = nickName;
    //  }

    // public String getPhone() {
    //    return phone;
    //  }

    //  public void setPhone(String phone) {
    //     this.phone = phone;
    //  }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
