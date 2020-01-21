package com.bsoft.repository;

import com.bsoft.entity.TreeDTO;
import com.bsoft.entity.TreeEntityDTO;
import com.bsoft.entity.UserOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2019/12/30.
 */
public interface UserOrganizationRepository extends CrudRepository<UserOrganization,Integer> {
    @Query(value = "SELECT * FROM USER_ORGANIZATION T WHERE T.LOGOFF=?1 START WITH T.ID=?2 CONNECT BY PRIOR T.ID=T.PARENT_ID;", nativeQuery = true)
    List<UserOrganization> findAllUserOrganizations(String logoff,String id);
    List<UserOrganization> findAllByLogoff(String logoff);

    @Query(value = "SELECT u.id as id,u.organizName as name,u.parentId as parentId,'1' as type  FROM UserOrganization u where u.logoff=?1 order by u.createDate desc")
    List<TreeDTO> findAllUserOrganizations(String logoff);

    @Query(value = "SELECT u.id as id,u.organizName as name,u.parentId as parentId,'1' as type  FROM UserOrganization u where u.logoff=?1 and u.parentId=?2 order by u.createDate desc")
    List<TreeDTO> findAllUserOrganizationsByParentId(String logoff,String parentId);

    @Query(value = "SELECT u.id as id,u.organizName as name,u.parentId as parentId,'1' as type  FROM UserOrganization u where u.logoff=?1 and u.parentId is null order by u.createDate desc")
    List<TreeDTO> findAllUserOrganizationsByParentIdIsNull(String logoff);

    @Query(value = "SELECT u.id as id,u.organizName as name,u.parentId as parentId,0 as rank  FROM UserOrganization u where u.logoff=?1 order by u.createDate desc")
    List<TreeEntityDTO> findTreeUserOrganizations(String logoff);
}
