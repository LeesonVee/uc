package com.bsoft.entity;/**
 * Arthur Administrator
 * date 2020/1/2.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *@ClassName SysDomain
 *@Description TODO
 *@Author Administrator
 *@Date 2020/1/2 18:53
 *@Version 1.0
 **/
@Entity
@Table(name="SYS_DOMAIN")
public class SysDomain implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String domainCode;
    private String domainName;
    private String remark;
    private String status;
    private String domainUrl;
    private Date createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SysDomain(){

    }
    public SysDomain(String domainCode, String domainName, String remark, String status, String domainUrl,Date createDate) {
        this.domainCode = domainCode;
        this.domainName = domainName;
        this.remark = remark;
        this.status = status;
        this.domainUrl = domainUrl;
        this.createDate = createDate;
    }
}
