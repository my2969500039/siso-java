package com.siso.web.member;

import com.siso.Result.Result;
import com.siso.response.android.login.userResponse;
import org.springframework.cache.annotation.CacheEvict;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

public interface MemberService {

    Result<List<userResponse>> all();
}
