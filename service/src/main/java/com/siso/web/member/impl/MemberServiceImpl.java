package com.siso.web.member.impl;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.androidUser;
import com.siso.repository.web.member.MemberRepository;
import com.siso.response.android.login.userResponse;
import com.siso.web.member.MemberService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Result<List<userResponse>> all() {
        List<androidUser>androidUsers=memberRepository.findAll();
        List<userResponse>userResponseList=new ArrayList<>();
        androidUsers.forEach(a->{
            userResponse userResponse=new userResponse();
            BeanUtils.copyProperties(a,userResponse);
            userResponseList.add(userResponse);
        });
        return Result.<List<userResponse>>builder().success().data(userResponseList).build();
    }


}
