package com.bsoft.repository;

import com.bsoft.entity.BaseUser;
import com.bsoft.entity.BaseUserRoles;
import com.bsoft.entity.BaseUserRolesDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Arthur Vee
 * date 2020/1/17.
 */
public interface BaseUserRolesRepository  extends CrudRepository<BaseUserRoles,Integer> {

//    <el-table-column prop="domainName" label="所属域"></el-table-column>
//                <el-table-column prop="roleName" label="职位"></el-table-column>
//                <el-table-column prop="organName" label="所属部门"></el-table-column>
    List<BaseUserRoles> getBaseUserRolesByUserIdAndLogoff(String userId,String logoff);
//    String getUserId();
//    String getRoleId();
//    String getDomain();
//    String getOrganId();
//    String getLogoff();
//    Date getLastLoginTime();
//    String getLastIpAddress();
//    Date getCreateTime();
    @Query(value = "select a.id as id,a.userId as userId,a.roleId as roleId,a.domain as domain," +
            "a.organId as organId,a.logoff as logoff,a.lastLoginTime as lastLoginTime,a.lastIpAddress as lastIpAddress," +
            "a.createTime as createTime,b.rolesName as roleName,c.domainName as domainName,d.organizName as organName from BaseUserRoles a left join SysRoles b" +
            " on a.roleId = b.id left join SysDomain c on a.domain = c.id  left join UserOrganization d on a.organId = d.id where a.userId=?1 and a.logoff=?2")
    List<BaseUserRolesDTO> getMixBaseUserRolesByUserIdAndLogoff(String userId,String logoff);
}
