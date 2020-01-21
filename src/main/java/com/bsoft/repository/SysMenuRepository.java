package com.bsoft.repository;

import com.bsoft.entity.SysMenus;
import com.bsoft.entity.TreeDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


/**
 * Arthur Vee
 * date 2020/1/13.
 */
public interface SysMenuRepository extends CrudRepository<SysMenus,Integer> {

    List<SysMenus> getAllByMenuParentIdAndStatus(String id,String status);

    @Modifying
    @Transactional
    @Query(value = "update SYS_MENUS set SUB_MENU_STATUS=?1,MODIFY_DATE=?2 where id=?3", nativeQuery = true)
    void updateSysMenus(String subMenuStatus,Date modifyDate,String pkey);
}
