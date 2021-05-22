package com.siso.repository.web.member;


import com.siso.entity.android.userManage.AndroidUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<AndroidUser,Long>, JpaSpecificationExecutor<AndroidUser> {
}
