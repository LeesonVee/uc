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
    private String organizcode;
    private String registernumber;
    private String organizsecondname;
    private String organizname;
    private String classifycode;
    private String organiztype;
    private String admindivision;
    private String address;
    private String streecode;
    private String streename;
    private String hostcode;
    private String parentid;
    private String subcode;
    private String zipcode;
    private String telphone;
    private String email;
    private Date founddate;
    private String legal;
    private Integer subnum;
    private Integer stationnum;
    private String pycode;
    private String grade;
    private String institlevel;
    private String buildingarea;
    private String chemicalmednum;
    private String chinesemednum;
    private String director;
    private String website;
    private String logoff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizcode() {
        return organizcode;
    }

    public void setOrganizcode(String organizcode) {
        this.organizcode = organizcode;
    }

    public String getRegisternumber() {
        return registernumber;
    }

    public void setRegisternumber(String registernumber) {
        this.registernumber = registernumber;
    }

    public String getOrganizsecondname() {
        return organizsecondname;
    }

    public void setOrganizsecondname(String organizsecondname) {
        this.organizsecondname = organizsecondname;
    }

    public String getOrganizname() {
        return organizname;
    }

    public void setOrganizname(String organizname) {
        this.organizname = organizname;
    }

    public String getClassifycode() {
        return classifycode;
    }

    public void setClassifycode(String classifycode) {
        this.classifycode = classifycode;
    }

    public String getOrganiztype() {
        return organiztype;
    }

    public void setOrganiztype(String organiztype) {
        this.organiztype = organiztype;
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

    public String getStreecode() {
        return streecode;
    }

    public void setStreecode(String streecode) {
        this.streecode = streecode;
    }

    public String getStreename() {
        return streename;
    }

    public void setStreename(String streename) {
        this.streename = streename;
    }

    public String getHostcode() {
        return hostcode;
    }

    public void setHostcode(String hostcode) {
        this.hostcode = hostcode;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public Date getFounddate() {
        return founddate;
    }

    public void setFounddate(Date founddate) {
        this.founddate = founddate;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public Integer getSubnum() {
        return subnum;
    }

    public void setSubnum(Integer subnum) {
        this.subnum = subnum;
    }

    public Integer getStationnum() {
        return stationnum;
    }

    public void setStationnum(Integer stationnum) {
        this.stationnum = stationnum;
    }

    public String getPycode() {
        return pycode;
    }

    public void setPycode(String pycode) {
        this.pycode = pycode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInstitlevel() {
        return institlevel;
    }

    public void setInstitlevel(String institlevel) {
        this.institlevel = institlevel;
    }

    public String getBuildingarea() {
        return buildingarea;
    }

    public void setBuildingarea(String buildingarea) {
        this.buildingarea = buildingarea;
    }

    public String getChemicalmednum() {
        return chemicalmednum;
    }

    public void setChemicalmednum(String chemicalmednum) {
        this.chemicalmednum = chemicalmednum;
    }

    public String getChinesemednum() {
        return chinesemednum;
    }

    public void setChinesemednum(String chinesemednum) {
        this.chinesemednum = chinesemednum;
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

    public UserOrganization() {
    }

    public UserOrganization(String organizcode, String registernumber, String organizsecondname, String organizname, String classifycode, String organiztype, String admindivision, String address, String streecode, String streename, String hostcode, String parentid, String subcode, String zipcode, String telphone, String email, Date founddate, String legal, Integer subnum, Integer stationnum, String pycode, String grade, String institlevel, String buildingarea, String chemicalmednum, String chinesemednum, String director, String website, String logoff) {
        this.organizcode = organizcode;
        this.registernumber = registernumber;
        this.organizsecondname = organizsecondname;
        this.organizname = organizname;
        this.classifycode = classifycode;
        this.organiztype = organiztype;
        this.admindivision = admindivision;
        this.address = address;
        this.streecode = streecode;
        this.streename = streename;
        this.hostcode = hostcode;
        this.parentid = parentid;
        this.subcode = subcode;
        this.zipcode = zipcode;
        this.telphone = telphone;
        this.email = email;
        this.founddate = founddate;
        this.legal = legal;
        this.subnum = subnum;
        this.stationnum = stationnum;
        this.pycode = pycode;
        this.grade = grade;
        this.institlevel = institlevel;
        this.buildingarea = buildingarea;
        this.chemicalmednum = chemicalmednum;
        this.chinesemednum = chinesemednum;
        this.director = director;
        this.website = website;
        this.logoff = logoff;
    }
}
