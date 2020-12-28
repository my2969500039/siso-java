package com.siso.android.notice;


import com.siso.request.android.noticet.noticeRequest;
import com.siso.Result.Result;
import com.siso.response.android.noticet.noticeResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface noticeService {
    Result<List<noticeResponse>> getnotice(noticeRequest noticeRequest);

    Result<noticeResponse> article(Long id,String type);
}
