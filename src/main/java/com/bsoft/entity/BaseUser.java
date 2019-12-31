package com.bsoft.entity;/**
 * Arthur Administrator
 * date 2019/12/27.
 */

/**
 *@ClassName TestUser
 *@Description TODO
 *@Author Administrator
 *@Date 2019/12/27 13:59
 *@Version 1.0
 **/

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="BASE_USER")
public class BaseUser {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="status")
    private String status;
//    @Column(name="createdt")
    private Date createdt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    public BaseUser() {

    }

    public BaseUser(String name, String password, String status, Date createdt) {
        this.name = name;
        this.password = password;
        this.status = status;
        this.createdt = createdt;
    }
}
