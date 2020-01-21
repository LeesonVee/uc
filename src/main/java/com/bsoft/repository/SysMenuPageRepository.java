package com.bsoft.repository;

import com.bsoft.entity.SysMenus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Arthur Vee
 * date 2020/1/13.
 */
public interface SysMenuPageRepository extends PagingAndSortingRepository<SysMenus,Integer> {

    Page<SysMenus> findAllByStatusAndMenuParentIdIsNull(String status,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuNameAndMenuParentIdIsNull(String status,String menuName,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuCodeAndMenuParentIdIsNull(String status,String menuCode,Pageable var1);

    Page<SysMenus> findAllByStatusAndOutRoleIdAndMenuParentIdIsNull(String status,String outRoleId,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuNameAndMenuCodeAndMenuParentIdIsNull(String status,String menuName,String menuCode,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuNameAndOutRoleIdAndMenuParentIdIsNull(String status,String menuName,String outRoleId,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuCodeAndOutRoleIdAndMenuParentIdIsNull(String status,String menuCode,String outRoleId,Pageable var1);

    Page<SysMenus> findAllByStatusAndMenuNameAndMenuCodeAndOutRoleIdAndMenuParentIdIsNull(String status,String menuName,String menuCode,String outRoleId,Pageable var1);


}
