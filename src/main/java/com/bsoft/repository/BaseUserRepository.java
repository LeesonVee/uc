package com.bsoft.repository;

import com.bsoft.entity.BaseUser;
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
    @Query(value = "SELECT * FROM base_user where name = ?1 AND PASSWORD=?2 AND STATUS=?3", nativeQuery = true)
    List<BaseUser> getLogonUsers(String name,String pwd,String status);
}
