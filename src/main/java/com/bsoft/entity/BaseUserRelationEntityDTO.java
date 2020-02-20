package com.bsoft.entity;

import java.util.Date;

/**
 * Arthur Vee
 * date 2020/1/16.
 */
public interface BaseUserRelationEntityDTO {
    String getId();
    String getLocalUserId();
    String getRemoteUserId();
    String getUsername();
    String getOrganId();
    Date getCreateDate();
    Date getModifyDate();
}
