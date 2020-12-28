package com.siso.repository.android.userLogin;


import com.siso.entity.android.userManage.androidUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface userLoginRepository extends JpaRepository<androidUser, String>, JpaSpecificationExecutor<androidUser> {

}
