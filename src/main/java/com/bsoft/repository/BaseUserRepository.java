package com.bsoft.repository;

import com.bsoft.entity.BaseUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Arthur Vee
 * date 2019/12/30.
 */
public interface BaseUserRepository extends CrudRepository<BaseUser,Integer> {
    @Query(value = "SELECT * FROM base_user where id = ?1", nativeQuery = true)
    BaseUser getBaseUserByPwd(String id);
}
