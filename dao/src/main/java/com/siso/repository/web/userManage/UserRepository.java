package com.siso.repository.web.userManage;


import com.siso.entity.web.userManage.adminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 帐户dao接口
 */
public interface UserRepository extends JpaRepository<adminUser,String>{

    // 用户登录
    adminUser findOneByNumber(String userNumber);

    Optional<adminUser> findById(Long Id);

    adminUser findOneById(Long Id);


    List<adminUser> findAllByParentId(Long parentId);


    //删除下级员工
    Integer deleteByNumber(String deleteNumber);




}
