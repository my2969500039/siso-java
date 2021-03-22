package com.siso.android.notice.impl;

import com.siso.Result.Result;
import com.siso.android.notice.noticeService;
import com.siso.entity.android.noticet.Notice;
import com.siso.entity.web.userManage.AdminUser;
import com.siso.repository.android.notice.NoticeRepository;
import com.siso.repository.web.userManage.UserRepository;
import com.siso.request.android.noticet.noticeRequest;
import com.siso.response.android.noticet.noticeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements noticeService {

    @Resource
    private NoticeRepository noticeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Result<List<noticeResponse>> getnotice(noticeRequest noticeRequest){
        List<Notice> Notices =noticeRepository.findAllByType(noticeRequest.getType());
        List<noticeResponse> noticeResponseList=new ArrayList<>();
        Notices.forEach(n->{
            noticeResponse noticeResponse=new noticeResponse();
            BeanUtils.copyProperties(n,noticeResponse);
            noticeResponseList.add(noticeResponse);
        });
        return Result.< List<noticeResponse>>builder().success().data(noticeResponseList).build();
    }

    @Override
    public Result<noticeResponse> article(Long id, String type) {
        Notice notice=noticeRepository.findAllByTypeAndId(type,id);
        AdminUser adminUser=userRepository.findOneById(notice.getId());
        noticeResponse noticeResponse=new noticeResponse();
        BeanUtils.copyProperties(notice,noticeResponse);
        noticeResponse.setAdmin(adminUser.getName());
        return Result.<noticeResponse>builder().success().data(noticeResponse).build();
    }
}
