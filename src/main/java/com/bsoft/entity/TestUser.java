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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_user")
public class TestUser {
    @Id
    /*@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")*/
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String testCol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCol() {
        return testCol;
    }

    public void setTestCol(String testCol) {
        this.testCol = testCol;
    }

    public TestUser() {

    }
    public TestUser(String testCol) {
        this.testCol = testCol;
    }
}
