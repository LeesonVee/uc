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
@Table(name="USER_ORGANIZATION")
public class UserOrganization {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String organizCode;
    private String registerNumber;
    private String organizSecondName;
    private String organizName;
    private String classifyCode;
    private String organizType;
    private String admindivision;
    private String address;
    private String streeCode;
    private String streeName;
    private String hostCode;
    private String parentId;
    private String subCode;
    private String zipCode;
    private String telphone;
    private String email;
    private Date foundDate;
    private String legal;
    private String subNum;
    private String stationNum;
    private String pyCode;
    private String grade;
    private String institLevel;
    private String buildingArea;
    private String chemicalmedNum;
    private String chinesemedNum;
    private String director;
    private String website;
    private String logoff;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizCode() {
        return organizCode;
    }

    public void setOrganizCode(String organizCode) {
        this.organizCode = organizCode;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getOrganizSecondName() {
        return organizSecondName;
    }

    public void setOrganizSecondName(String organizSecondName) {
        this.organizSecondName = organizSecondName;
    }

    public String getOrganizName() {
        return organizName;
    }

    public void setOrganizName(String organizName) {
        this.organizName = organizName;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getOrganizType() {
        return organizType;
    }

    public void setOrganizType(String organizType) {
        this.organizType = organizType;
    }

    public String getAdmindivision() {
        return admindivision;
    }

    public void setAdmindivision(String admindivision) {
        this.admindivision = admindivision;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreeCode() {
        return streeCode;
    }

    public void setStreeCode(String streeCode) {
        this.streeCode = streeCode;
    }

    public String getStreeName() {
        return streeName;
    }

    public void setStreeName(String streeName) {
        this.streeName = streeName;
    }

    public String getHostCode() {
        return hostCode;
    }

    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getSubNum() {
        return subNum;
    }

    public void setSubNum(String subNum) {
        this.subNum = subNum;
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInstitLevel() {
        return institLevel;
    }

    public void setInstitLevel(String institLevel) {
        this.institLevel = institLevel;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getChemicalmedNum() {
        return chemicalmedNum;
    }

    public void setChemicalmedNum(String chemicalmedNum) {
        this.chemicalmedNum = chemicalmedNum;
    }

    public String getChinesemedNum() {
        return chinesemedNum;
    }

    public void setChinesemedNum(String chinesemedNum) {
        this.chinesemedNum = chinesemedNum;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoff() {
        return logoff;
    }

    public void setLogoff(String logoff) {
        this.logoff = logoff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserOrganization() {
    }

    public UserOrganization(String organizCode, String registerNumber, String organizSecondName, String organizName, String classifyCode, String organizType, String admindivision, String address, String streeCode, String streeName, String hostCode, String parentId, String subCode, String zipCode, String telphone, String email, Date foundDate, String legal, String subNum, String stationNum, String pyCode, String grade, String institLevel, String buildingArea, String chemicalmedNum, String chinesemedNum, String director, String website, String logoff, Date createDate) {
        this.organizCode = organizCode;
        this.registerNumber = registerNumber;
        this.organizSecondName = organizSecondName;
        this.organizName = organizName;
        this.classifyCode = classifyCode;
        this.organizType = organizType;
        this.admindivision = admindivision;
        this.address = address;
        this.streeCode = streeCode;
        this.streeName = streeName;
        this.hostCode = hostCode;
        this.parentId = parentId;
        this.subCode = subCode;
        this.zipCode = zipCode;
        this.telphone = telphone;
        this.email = email;
        this.foundDate = foundDate;
        this.legal = legal;
        this.subNum = subNum;
        this.stationNum = stationNum;
        this.pyCode = pyCode;
        this.grade = grade;
        this.institLevel = institLevel;
        this.buildingArea = buildingArea;
        this.chemicalmedNum = chemicalmedNum;
        this.chinesemedNum = chinesemedNum;
        this.director = director;
        this.website = website;
        this.logoff = logoff;
        this.createDate = createDate;
    }
}
