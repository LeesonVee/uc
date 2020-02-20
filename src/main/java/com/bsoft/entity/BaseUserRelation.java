package com.bsoft.entity;
/**
 * Arthur Vee
 * date 2020/2/18.
 */

/**
 *@ClassName BaseUserRelation
 *@Description TODO
 *@Author Vee
 *@Date 2020/2/18 13:38
 *@Version 1.0
 **/

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="BASE_USER_RELATION")
public class BaseUserRelation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String localUserId;
    private String remoteUserId;
    private String username;
    private String privateKey;
    private String publicKey;
    private String organId;
    private String centerKey;
    private Date createDate;
    private Date modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(String localUserId) {
        this.localUserId = localUserId;
    }

    public String getRemoteUserId() {
        return remoteUserId;
    }

    public void setRemoteUserId(String remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getCenterKey() {
        return centerKey;
    }

    public void setCenterKey(String centerKey) {
        this.centerKey = centerKey;
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

    public BaseUserRelation() {}

    public BaseUserRelation(String localUserId, String remoteUserId, String username, String privateKey, String publicKey, String organId, String centerKey, Date createDate, Date modifyDate) {
        this.localUserId = localUserId;
        this.remoteUserId = remoteUserId;
        this.username = username;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.organId = organId;
        this.centerKey = centerKey;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
