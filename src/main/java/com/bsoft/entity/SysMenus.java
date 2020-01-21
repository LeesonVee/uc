package com.bsoft.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *@ClassName SysMenus
 *@Description TODO
 *@Author Vee
 *@Date 2020/1/10 15:10
 *@Version 1.0
 **/
@Entity
@Table(name="SYS_MENUS")
public class SysMenus {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String menuName;
    private String menuCode;
    private Integer menuLevel;
    private String menuPath;
    private String menuParentId;
    private String outRoleId;
    private String status;
    private String subMenuStatus;
    private Date modifyDate;
    private Date createDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuPath() {
        return menuPath;
    }
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuParentId() {
        return menuParentId;
    }
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getOutRoleId() {
        return outRoleId;
    }
    public void setOutRoleId(String outRoleId) {
        this.outRoleId = outRoleId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubMenuStatus() {
        return subMenuStatus;
    }
    public void setSubMenuStatus(String subMenuStatus) {
        this.subMenuStatus = subMenuStatus;
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
    public SysMenus(){}
    public SysMenus(String id, String menuName, String menuCode, Integer menuLevel, String menuPath, String menuParentId, String outRoleId, String status, String subMenuStatus, Date modifyDate, Date createDate) {
        this.id = id;
        this.menuName = menuName;
        this.menuCode = menuCode;
        this.menuLevel = menuLevel;
        this.menuPath = menuPath;
        this.menuParentId = menuParentId;
        this.outRoleId = outRoleId;
        this.status = status;
        this.subMenuStatus = subMenuStatus;
        this.modifyDate = modifyDate;
        this.createDate = createDate;
    }
}
