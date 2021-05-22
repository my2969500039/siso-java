package com.siso.repository.android.userLogin;


import com.siso.entity.android.userManage.AndroidUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface androidUserRepository extends JpaRepository<AndroidUser,String> {

    //安卓用户登录
//    @Select("select * from  android_user where number=#{number}")
    AndroidUser findOneByNumber (String number);

    AndroidUser findOneById(Long id);

    Long countAllByNumber(String number);


    //获取商铺下新用户数(七天内7内用户/超管)
    List<AndroidUser> findAllByCreateTimeGreaterThanEqual(Date time);

}
