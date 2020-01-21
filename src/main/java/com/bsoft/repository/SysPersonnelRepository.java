package com.bsoft.repository;

import com.bsoft.entity.SysPersonnel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2020/1/15.
 */
public interface SysPersonnelRepository extends CrudRepository<SysPersonnel,Integer> {
    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOff(String organId, String logoff);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOff(String officeId, String logoff);


    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOffAndMobile(String organId, String logoff,String mobile);
    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOffAndPersonId(String organId, String logoff,String personId);
    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOffAndGh(String organId, String logoff,String gh);
    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOffAndPersonName(String organId, String logoff,String personName);
    List<SysPersonnel> findSysPersonnelsByOrganizCodeAndLogOffAndCardNum(String organId, String logoff,String cardNum);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOffAndMobile(String officeId, String logoff,String mobile);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOffAndPersonId(String officeId, String logoff,String personId);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOffAndGh(String officeId, String logoff,String gh);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOffAndPersonName(String officeId, String logoff,String personName);
    List<SysPersonnel> findSysPersonnelsByOfficeCodeAndLogOffAndCardNum(String officeId, String logoff,String cardNum);
}
