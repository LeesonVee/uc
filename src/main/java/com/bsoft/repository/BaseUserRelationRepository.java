package com.bsoft.repository;

import com.bsoft.entity.BaseUserRelation;
import com.bsoft.entity.BaseUserRelationEntityDTO;
import com.bsoft.entity.CommonEntityDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


/**
 * Arthur Vee
 * date 2020/2/18 13:38
 */
public interface BaseUserRelationRepository extends CrudRepository<BaseUserRelation,Integer> {

    List<BaseUserRelationEntityDTO> getBaseUserRelationByOrganId(String organId);

    @Modifying
    @Transactional
    @Query(value = "update BASE_USER_RELATION set LOCAL_USER_ID=?1,USERNAME=?2,MODIFY_DATE=?3 where id=?4", nativeQuery = true)
    void updateBaseUserRelationById(String localUserId, String username,Date modifyDate, String id);

    List<BaseUserRelation> getBaseUserRelationByCenterKeyAndRemoteUserId(String centerKey,String remoteUserId);

    @Modifying
    @Transactional
    @Query(value = "delete from  BASE_USER_RELATION where id=?1", nativeQuery = true)
    void delBaseUserRelationById(String id);

    List<BaseUserRelation> findBaseUserRelationByIdAndCenterKey(String id,String centerKey);

    List<BaseUserRelationEntityDTO> getBaseUserRelationByOrganIdAndUsername(String organId,String username);
    List<BaseUserRelationEntityDTO> getBaseUserRelationByOrganIdAndLocalUserId(String organId,String localUserId);
    List<BaseUserRelationEntityDTO> getBaseUserRelationByOrganIdAndRemoteUserId(String organId,String remoteUserId);
}
