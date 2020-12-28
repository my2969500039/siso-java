package com.siso.web.upload;

import com.siso.Result.Result;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {
    Result<String> upload(HttpServletRequest request) throws Exception;
}
