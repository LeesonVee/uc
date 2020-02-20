package com.bsoft.repository;

import com.bsoft.entity.BaseUser;
import com.bsoft.entity.UserMixEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2019/12/30.
 */
public interface BaseUserRepository extends CrudRepository<BaseUser,Integer> {
    @Query(value = "SELECT * FROM base_user where id = ?1", nativeQuery = true)
    BaseUser getBaseUserByPwd(String id);
    @Query(value = "SELECT * FROM base_user where (name = ?1 OR person_id = ?2) AND PASSWORD=?3 AND STATUS=?4", nativeQuery = true)
    List<BaseUser> getLogonUsers(String name,String personId,String pwd,String status);
    @Query(value = "select a.id as id,b.personId as personId,b.personName as personName,b.cardNum as cardNum," +
            "a.status as status,b.gender as gender,b.mobile as mobile,b.pyCode as pyCode,a.createdt as createDt" +
            " from BaseUser a left join SysPersonnel b on a.personId=b.personId where a.status=?1 and b.organizCode=?2")
    List<UserMixEntity> getUserMixsByStatusAndOrganCode(String status,String organCode);
    @Query(value = "select a.id as id,b.personId as personId,b.personName as personName,b.cardNum as cardNum," +
            "a.status as status,b.gender as gender,b.mobile as mobile,b.pyCode as pyCode,a.createdt as createDt" +
            " from BaseUser a left join SysPersonnel b on a.personId=b.personId where a.status=?1 and b.organizCode=?2 and b.personId=?3")
    List<UserMixEntity> getUserMixsByStatusAndOrganCodeAndPersonnelId(String status,String organCode,String personId);
    @Query(value = "select a.id as id,b.personId as personId,b.personName as personName,b.cardNum as cardNum," +
            "a.status as status,b.gender as gender,b.mobile as mobile,b.pyCode as pyCode,a.createdt as createDt" +
            " from BaseUser a left join SysPersonnel b on a.personId=b.personId where a.status=?1 and b.organizCode=?2 and b.personName=?3")
    List<UserMixEntity> getUserMixsByStatusAndOrganCodeAndPersonName(String status,String organCode,String personName);
    @Query(value = "select a.id as id,b.personId as personId,b.personName as personName,b.cardNum as cardNum," +
            "a.status as status,b.gender as gender,b.mobile as mobile,b.pyCode as pyCode,a.createdt as createDt" +
            " from BaseUser a left join SysPersonnel b on a.personId=b.personId where a.status=?1 and b.organizCode=?2 and b.cardNum=?3")
    List<UserMixEntity> getUserMixsByStatusAndOrganCodeAndCardNum(String status,String organCode,String cardNum);

}
