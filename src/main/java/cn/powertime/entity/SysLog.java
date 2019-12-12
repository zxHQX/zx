package cn.powertime.entity;

import java.io.Serializable;

public class SysLog implements Serializable {


    private String id;

    private Integer type;

    private String descInfo;

    private String remark;

    private String adr;

    private int backups;

    private int isSuccess;

    private String createTime;

    private String createUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    
    public void setType(Integer type) {
        this.type = type;
    }

  
    public String getDescInfo() {
        return descInfo;
    }

   
    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo == null ? null : descInfo.trim();
    }

  
    public String getRemark() {
        return remark;
    }

  
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    
    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr == null ? null : adr.trim();
    }

    public int getBackups() {
        return backups;
    }

    public void setBackups(int backups) {
        this.backups = backups;
    }


    public int getIsSuccess() {
        return isSuccess;
    }

   
    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

  
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

 
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", descInfo='" + descInfo + '\'' +
                ", remark='" + remark + '\'' +
                ", adr='" + adr + '\'' +
                ", backups=" + backups +
                ", isSuccess=" + isSuccess +
                ", createTime='" + createTime + '\'' +
                ", createUserId='" + createUserId + '\'' +
                '}';
    }
}