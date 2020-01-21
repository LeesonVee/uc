package com.bsoft.entity;

/**
 *@ClassName SysOffice
 *@Description TODO
 *@Author Administrator
 *@Date 2020/1/7 16:40
 *@Version 1.0
 **/

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SYS_OFFICE")
public class SysOffice {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String officeCode;
    private String officeName;
    private String organizCode;
    private String organizType;
    private String parentId;
    private String address;
    private String outPatientClinic;
    private String medicalLab;
    private String hospitalArea;
    private int ratedBed;
    private String telphone;
    private String email;
    private String plsx;
    private String pyCode;
    private String logoff;
    private String ksxsgs;
    private String ksxsgsCode;
    private String hospitalDept;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOrganizCode() {
        return organizCode;
    }

    public void setOrganizCode(String organizCode) {
        this.organizCode = organizCode;
    }

    public String getOrganizType() {
        return organizType;
    }

    public void setOrganizType(String organizType) {
        this.organizType = organizType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOutPatientClinic() {
        return outPatientClinic;
    }

    public void setOutPatientClinic(String outPatientClinic) {
        this.outPatientClinic = outPatientClinic;
    }

    public String getMedicalLab() {
        return medicalLab;
    }

    public void setMedicalLab(String medicalLab) {
        this.medicalLab = medicalLab;
    }

    public String getHospitalArea() {
        return hospitalArea;
    }

    public void setHospitalArea(String hospitalArea) {
        this.hospitalArea = hospitalArea;
    }

    public int getRatedBed() {
        return ratedBed;
    }

    public void setRatedBed(int ratedBed) {
        this.ratedBed = ratedBed;
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

    public String getPlsx() {
        return plsx;
    }

    public void setPlsx(String plsx) {
        this.plsx = plsx;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getLogoff() {
        return logoff;
    }

    public void setLogoff(String logoff) {
        this.logoff = logoff;
    }

    public String getKsxsgs() {
        return ksxsgs;
    }

    public void setKsxsgs(String ksxsgs) {
        this.ksxsgs = ksxsgs;
    }

    public String getKsxsgsCode() {
        return ksxsgsCode;
    }

    public void setKsxsgsCode(String ksxsgsCode) {
        this.ksxsgsCode = ksxsgsCode;
    }

    public String getHospitalDept() {
        return hospitalDept;
    }

    public void setHospitalDept(String hospitalDept) {
        this.hospitalDept = hospitalDept;
    }
    public SysOffice(){

    }
    public SysOffice(String officeCode, String officeName, String organizCode, String organizType, String parentId, String address, String outPatientClinic, String medicalLab, String hospitalArea, int ratedBed, String telphone, String email, String plsx, String pyCode, String logoff, String ksxsgs, String ksxsgsCode, String hospitalDept) {
        this.officeCode = officeCode;
        this.officeName = officeName;
        this.organizCode = organizCode;
        this.organizType = organizType;
        this.parentId = parentId;
        this.address = address;
        this.outPatientClinic = outPatientClinic;
        this.medicalLab = medicalLab;
        this.hospitalArea = hospitalArea;
        this.ratedBed = ratedBed;
        this.telphone = telphone;
        this.email = email;
        this.plsx = plsx;
        this.pyCode = pyCode;
        this.logoff = logoff;
        this.ksxsgs = ksxsgs;
        this.ksxsgsCode = ksxsgsCode;
        this.hospitalDept = hospitalDept;
    }
}
