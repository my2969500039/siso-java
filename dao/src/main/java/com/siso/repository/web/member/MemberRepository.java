package com.siso.repository.web.member;


import com.siso.entity.android.userManage.androidUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<androidUser,Long> {
}
