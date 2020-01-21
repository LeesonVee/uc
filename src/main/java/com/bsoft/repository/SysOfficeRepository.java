package com.bsoft.repository;

import com.bsoft.entity.SysOffice;
import com.bsoft.entity.TreeDTO;
import com.bsoft.entity.TreeEntityDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2020/1/7.
 */
public interface SysOfficeRepository  extends CrudRepository<SysOffice,Integer> {

    @Query(value = "SELECT o.id as id,o.officeName as name,o.parentId as parentId FROM SysOffice o where o.logoff=?1 and o.organizCode=?2 order by o.id")
    List<TreeDTO> findAllSysOffices(String logoff,String organizCode);

    public SysOffice findAllById(String id);

    @Query(value = "SELECT o.id as id,o.officeName as name,o.parentId as parentId,'2' as type FROM SysOffice o where o.logoff=?1 and o.organizCode=?2 order by o.id")
    List<TreeDTO> findAllSysOfficesByOrganizCode(String logoff,String organizCode);

    @Query(value = "SELECT o.id as id,o.officeName as name,o.parentId as parentId,0 as rank FROM SysOffice o where o.logoff=?1 and o.organizCode=?2 order by o.id")
    List<TreeEntityDTO> findTreeSysOfficesByOrganizCode(String logoff, String organizCode);
}
