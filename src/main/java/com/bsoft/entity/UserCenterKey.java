package com.bsoft.entity;
/**
 * Arthur Vee
 * date 2020/2/17.
 */

/**
 *@ClassName UserCenterKey
 *@Description TODO
 *@Author Vee
 *@Date 2020/2/17 23:38
 *@Version 1.0
 **/

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_CENTER_KEY")
public class UserCenterKey {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column(name="centerKey")
    private String centerKey;
    @Column(name="status")
    private String status;
    @Column(name="createDate")
    private Date createDate;
    @Column(name="modifyDate")
    private Date modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCenterKey() {
        return centerKey;
    }

    public void setCenterKey(String centerKey) {
        this.centerKey = centerKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public UserCenterKey() {}

    public UserCenterKey(String centerKey, String status, Date createDate, Date modifyDate) {
        this.centerKey = centerKey;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
