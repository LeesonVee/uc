package com.bsoft.repository;

import com.bsoft.entity.BaseUser;
import com.bsoft.entity.SysDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2020/01/02 19:53
 */
public interface SysDomainRepository extends CrudRepository<SysDomain,Integer> {
    @Query(value = "SELECT * FROM SYS_DOMAIN where status = ?1 ORDER BY CREATE_DATE", nativeQuery = true)
    List<SysDomain> getSysDomains( String status);

    List<SysDomain> getAllSysDomainsByStatusOrderByCreateDateDesc(String status);

    List<SysDomain> getSysDomainsByStatusAndId(String status,String id);
}
