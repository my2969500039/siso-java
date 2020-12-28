package com.siso.web.member;

import com.siso.Result.Result;
import com.siso.response.android.login.userResponse;

import java.util.List;

public interface MemberService {
    Result<List<userResponse>> all();
}
