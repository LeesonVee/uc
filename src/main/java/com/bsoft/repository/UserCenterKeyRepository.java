package com.bsoft.repository;

import com.bsoft.entity.CommonEntityDTO;
import com.bsoft.entity.UserCenterKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2020/2/17 23:38
 */
public interface UserCenterKeyRepository extends CrudRepository<UserCenterKey,Integer> {

    public List<UserCenterKey> getAllUserCenterKeyByStatusIsNotOrderByModifyDateDesc(String status);

    public List<UserCenterKey> getAllUserCenterKeyByStatusOrderByModifyDateDesc(String status);

    public List<UserCenterKey> getAllUserCenterKeyByStatusIsNotAndCenterKeyOrderByModifyDateDesc(String status,String centerKey);

    public List<UserCenterKey> getAllUserCenterKeyByStatusAndCenterKeyOrderByModifyDateDesc(String status,String centerKey);

}
