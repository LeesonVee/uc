package com.bsoft.entity;

/**
 *用户角色表
 *@Description TODO
 *@Author Vee
 *@Date 2020/1/17 9:58
 *@Version 1.0
 **/

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="BASE_USERROLES")
public class BaseUserRoles {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String userId;
    private String roleId;
    private String domain;
    private String organId;
    private String logoff;
    private Date lastLoginTime;
    private String lastIpAddress;
    private Date createTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getOrganId() {
        return organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getLogoff() {
        return logoff;
    }
    public void setLogoff(String logoff) {
        this.logoff = logoff;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastIpAddress() {
        return lastIpAddress;
    }

    public void setLastIpAddress(String lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BaseUserRoles(){}

    public BaseUserRoles(String userId, String roleId, String domain, String organId, String logoff, Date lastLoginTime, String lastIpAddress, Date createTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.domain = domain;
        this.organId = organId;
        this.logoff = logoff;
        this.lastLoginTime = lastLoginTime;
        this.lastIpAddress = lastIpAddress;
        this.createTime = createTime;
    }
}
