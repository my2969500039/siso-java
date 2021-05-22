package com.siso.web.member;

import com.siso.Result.Result;
import com.siso.entity.android.userManage.AndroidUser;
import com.siso.request.web.member.SearchRequest;
import com.siso.response.android.login.userResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

public interface MemberService {

    Result<Page<AndroidUser>> page(SearchRequest request);
}
