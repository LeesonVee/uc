package com.bsoft.entity;

import java.util.Date;

/**
 * Arthur Vee
 * date 2020/1/20.
 */
public interface BaseUserRolesDTO {
    String getId();
    String getUserId();
    String getRoleId();
    String getDomain();
    String getOrganId();
    String getLogoff();
    Date getLastLoginTime();
    String getLastIpAddress();
    Date getCreateTime();
    String getDomainName();
    String getRoleName();
    String getOrganName();
}
