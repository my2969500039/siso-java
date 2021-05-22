package com.siso.web.member.impl;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.AndroidUser;
import com.siso.repository.web.member.MemberRepository;
import com.siso.request.web.member.SearchRequest;
import com.siso.response.android.login.userResponse;
import com.siso.web.member.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Result<Page<AndroidUser>> page(SearchRequest request) {
        Pageable pageable= PageRequest.of(request.getPageNum(), request.getPageSize());
        Specification<AndroidUser>specification=(Specification<AndroidUser>)(root,cb,cq)->{
            List<Predicate>predicates=new LinkedList<>();
            if (request.getType().equals("1")){
                predicates.add(cq.greaterThan(root.get("createTime"),getWeek()));
            }
            if (StringUtils.isNotBlank(request.getKeyWord())){
                predicates.add(cq.like(root.get("name"), request.getKeyWord()));
            }
            return cq.and(predicates.toArray(new Predicate[0]));
        };
        Page<AndroidUser> AndroidUsers =memberRepository.findAll(specification,pageable);
        return Result.<Page<AndroidUser>>builder().success().data(AndroidUsers).build();
    }

    public Date getWeek(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        return c.getTime();
    }


}
