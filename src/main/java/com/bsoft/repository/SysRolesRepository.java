package com.bsoft.repository;

import com.bsoft.entity.SysRoles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Administrator
 * date 2020/1/10.
 */
public interface SysRolesRepository extends CrudRepository<SysRoles,Integer> {
    public List<SysRoles> getAllSysRolesByStatusOrderByModifyDateDesc(String status);
    public List<SysRoles> getAllSysRolesByStatusAndOutDomainIdOrderByModifyDateDesc(String status,String outDomainId);
}
