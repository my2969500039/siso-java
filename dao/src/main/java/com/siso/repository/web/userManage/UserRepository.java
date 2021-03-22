package com.siso.repository.web.userManage;


import com.siso.entity.web.userManage.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 帐户dao接口
 */
public interface UserRepository extends JpaRepository<AdminUser,String>{

    // 用户登录
    AdminUser findOneByNumber(String userNumber);

    Optional<AdminUser> findById(Long Id);

    AdminUser findOneById(Long Id);


    List<AdminUser> findAllByParentId(Long parentId);


    //删除下级员工
    Integer deleteByNumber(String deleteNumber);




}
