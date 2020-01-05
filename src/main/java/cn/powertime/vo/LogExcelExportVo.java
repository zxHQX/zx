package cn.powertime.vo;

import cn.powertime.entity.SysLog;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

/**
 * @author zx
 * @version V1.0
 * @Package com.powertime.audiltor.vo
 * @date 2019/11/5 22:36
 */

public class LogExcelExportVo extends BaseRowModel implements Serializable{


    public LogExcelExportVo(){}
    public LogExcelExportVo(SysLog sysLog, cn.powertime.entity.SysUser sysUser) {
        this.username = sysUser.getUsername();
        this.account = sysUser.getAccount();
        this.type = sysLog.getType();
        this.descInfo = sysLog.getDescInfo();
        this.remark = sysLog.getRemark();
        this.adr = sysLog.getAdr();
        this.backups = sysLog.getBackups();
        this.isSuccess = sysLog.getIsSuccess();
        this.createTime = sysLog.getCreateTime();
    }

    @ExcelProperty(value = "账号", index = 0)
    private String account;
    @ExcelProperty(value = "姓名", index = 1)
    private String username;

    @ExcelProperty(value = "日志类型", index = 2)
    private Integer type;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ExcelProperty(value = "日志说明", index = 3)
    private String descInfo;

    @ExcelProperty(value = "操作详情", index = 4)
    private String remark;

    @ExcelProperty(value = "用户端口号", index = 5)
    private String adr;

    @ExcelProperty(value = "是否备份", index = 6)
    private int backups;

    @ExcelProperty(value = "是否记录成功", index = 7)
    private int isSuccess;

    @ExcelProperty(value = "创建时间", index = 8)
    private String createTime;



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

}
