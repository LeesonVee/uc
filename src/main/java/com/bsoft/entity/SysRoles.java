package com.bsoft.entity;/**
 * Arthur Administrator
 * date 2020/1/10.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *@ClassName SysRoles
 *@Description TODO
 *@Author Administrator
 *@Date 2020/1/10 18:10
 *@Version 1.0
 **/
@Entity
@Table(name="SYS_ROLES")
public class SysRoles {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String rolesName;
    private String rolesCode;
    private String outDomainId;
    private String status;
    private Date modifyDate;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesCode() {
        return rolesCode;
    }

    public void setRolesCode(String rolesCode) {
        this.rolesCode = rolesCode;
    }

    public String getOutDomainId() {
        return outDomainId;
    }

    public void setOutDomainId(String outDomainId) {
        this.outDomainId = outDomainId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public SysRoles(){}
    public SysRoles(String rolesName, String rolesCode, String outDomainId, String status, Date modifyDate, Date createDate) {
        this.rolesName = rolesName;
        this.rolesCode = rolesCode;
        this.outDomainId = outDomainId;
        this.status = status;
        this.modifyDate = modifyDate;
        this.createDate = createDate;
    }
}
