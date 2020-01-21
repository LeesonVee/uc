package com.bsoft.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="SYS_PERSONNEL")
public class SysPersonnel {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  private String id;
  private String personId;
  private String personName;
  private String photo;
  private String cardType;
  private String cardNum;
  private Date birthday;
  private String gender;
  private String ethnic;
  private String hometown;
  private String email;
  private String mobile;
  private String education;
  private String educationBackground;
  private Date joininWork;
  private String jobPost;
  private String operationType;
  private String medicalRoles;
  private String majorType;
  private String majorJob;
  private String officeCode;
  private String organizCode;
  private String address;
  private String pyCode;
  private String remark;
  private String certificateNum;
  private String majorQualify;
  private String operationScope;
  private String majorName;
  private String isExpert;
  private String isCancel;
  private Double expertCost;
  private String prescribeRight;
  private String narcoticRight;
  private String psychotropicRight;
  private String antibioticRight;
  private Date lastModifyDate;
  private String logOff;
  private String wbCode;
  private String jxCode;
  private String qtCode;
  private String antibioticLevel;
  private String gh;
  private String gpRole;
  private String zybh;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEthnic() {
    return ethnic;
  }

  public void setEthnic(String ethnic) {
    this.ethnic = ethnic;
  }

  public String getHometown() {
    return hometown;
  }

  public void setHometown(String hometown) {
    this.hometown = hometown;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getEducationBackground() {
    return educationBackground;
  }

  public void setEducationBackground(String educationBackground) {
    this.educationBackground = educationBackground;
  }

  public Date getJoininWork() {
    return joininWork;
  }

  public void setJoininWork(Date joininWork) {
    this.joininWork = joininWork;
  }

  public String getJobPost() {
    return jobPost;
  }

  public void setJobPost(String jobPost) {
    this.jobPost = jobPost;
  }

  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public String getMedicalRoles() {
    return medicalRoles;
  }

  public void setMedicalRoles(String medicalRoles) {
    this.medicalRoles = medicalRoles;
  }

  public String getMajorType() {
    return majorType;
  }

  public void setMajorType(String majorType) {
    this.majorType = majorType;
  }

  public String getMajorJob() {
    return majorJob;
  }

  public void setMajorJob(String majorJob) {
    this.majorJob = majorJob;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOrganizCode() {
    return organizCode;
  }

  public void setOrganizCode(String organizCode) {
    this.organizCode = organizCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPyCode() {
    return pyCode;
  }

  public void setPyCode(String pyCode) {
    this.pyCode = pyCode;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCertificateNum() {
    return certificateNum;
  }

  public void setCertificateNum(String certificateNum) {
    this.certificateNum = certificateNum;
  }

  public String getMajorQualify() {
    return majorQualify;
  }

  public void setMajorQualify(String majorQualify) {
    this.majorQualify = majorQualify;
  }

  public String getOperationScope() {
    return operationScope;
  }

  public void setOperationScope(String operationScope) {
    this.operationScope = operationScope;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getIsExpert() {
    return isExpert;
  }

  public void setIsExpert(String isExpert) {
    this.isExpert = isExpert;
  }

  public String getIsCancel() {
    return isCancel;
  }

  public void setIsCancel(String isCancel) {
    this.isCancel = isCancel;
  }

  public Double getExpertCost() {
    return expertCost;
  }

  public void setExpertCost(Double expertCost) {
    this.expertCost = expertCost;
  }

  public String getPrescribeRight() {
    return prescribeRight;
  }

  public void setPrescribeRight(String prescribeRight) {
    this.prescribeRight = prescribeRight;
  }

  public String getNarcoticRight() {
    return narcoticRight;
  }

  public void setNarcoticRight(String narcoticRight) {
    this.narcoticRight = narcoticRight;
  }

  public String getPsychotropicRight() {
    return psychotropicRight;
  }

  public void setPsychotropicRight(String psychotropicRight) {
    this.psychotropicRight = psychotropicRight;
  }

  public String getAntibioticRight() {
    return antibioticRight;
  }

  public void setAntibioticRight(String antibioticRight) {
    this.antibioticRight = antibioticRight;
  }

  public Date getLastModifyDate() {
    return lastModifyDate;
  }

  public void setLastModifyDate(Date lastModifyDate) {
    this.lastModifyDate = lastModifyDate;
  }

  public String getLogOff() {
    return logOff;
  }

  public void setLogOff(String logOff) {
    this.logOff = logOff;
  }

  public String getWbCode() {
    return wbCode;
  }

  public void setWbCode(String wbCode) {
    this.wbCode = wbCode;
  }

  public String getJxCode() {
    return jxCode;
  }

  public void setJxCode(String jxCode) {
    this.jxCode = jxCode;
  }

  public String getQtCode() {
    return qtCode;
  }

  public void setQtCode(String qtCode) {
    this.qtCode = qtCode;
  }

  public String getAntibioticLevel() {
    return antibioticLevel;
  }

  public void setAntibioticLevel(String antibioticLevel) {
    this.antibioticLevel = antibioticLevel;
  }

  public String getGh() {
    return gh;
  }

  public void setGh(String gh) {
    this.gh = gh;
  }

  public String getGpRole() {
    return gpRole;
  }

  public void setGpRole(String gpRole) {
    this.gpRole = gpRole;
  }

  public String getZybh() {
    return zybh;
  }

  public void setZybh(String zybh) {
    this.zybh = zybh;
  }

  public SysPersonnel(){}

  public SysPersonnel(String personId, String personName, String photo, String cardType, String cardNum, Date birthday, String gender, String ethnic, String hometown, String email, String mobile, String education, String educationBackground, Date joininWork, String jobPost, String operationType, String medicalRoles, String majorType, String majorJob, String officeCode, String organizCode, String address, String pyCode, String remark, String certificateNum, String majorQualify, String operationScope, String majorName, String isExpert, String isCancel, Double expertCost, String prescribeRight, String narcoticRight, String psychotropicRight, String antibioticRight, Date lastModifyDate, String logOff, String wbCode, String jxCode, String qtCode, String antibioticLevel, String gh, String gpRole, String zybh) {
    this.personId = personId;
    this.personName = personName;
    this.photo = photo;
    this.cardType = cardType;
    this.cardNum = cardNum;
    this.birthday = birthday;
    this.gender = gender;
    this.ethnic = ethnic;
    this.hometown = hometown;
    this.email = email;
    this.mobile = mobile;
    this.education = education;
    this.educationBackground = educationBackground;
    this.joininWork = joininWork;
    this.jobPost = jobPost;
    this.operationType = operationType;
    this.medicalRoles = medicalRoles;
    this.majorType = majorType;
    this.majorJob = majorJob;
    this.officeCode = officeCode;
    this.organizCode = organizCode;
    this.address = address;
    this.pyCode = pyCode;
    this.remark = remark;
    this.certificateNum = certificateNum;
    this.majorQualify = majorQualify;
    this.operationScope = operationScope;
    this.majorName = majorName;
    this.isExpert = isExpert;
    this.isCancel = isCancel;
    this.expertCost = expertCost;
    this.prescribeRight = prescribeRight;
    this.narcoticRight = narcoticRight;
    this.psychotropicRight = psychotropicRight;
    this.antibioticRight = antibioticRight;
    this.lastModifyDate = lastModifyDate;
    this.logOff = logOff;
    this.wbCode = wbCode;
    this.jxCode = jxCode;
    this.qtCode = qtCode;
    this.antibioticLevel = antibioticLevel;
    this.gh = gh;
    this.gpRole = gpRole;
    this.zybh = zybh;
  }
}
