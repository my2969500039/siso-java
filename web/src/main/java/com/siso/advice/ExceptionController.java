package com.siso.advice;


import com.siso.Result.ResultMap;
import com.siso.exception.NormalException;
import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    private final ResultMap resultMap;

    @Autowired
    public ExceptionController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ResultMap handle401() {
        return resultMap.fail().code(403).message("您没有权限访问！");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultMap jsonParseError(HttpServletRequest request, Throwable ex) {
        return resultMap.fail().code(getStatus(request).value()).message("JSON格式错误，无法解析对象");
    }

    @ExceptionHandler(NormalException.class)
    public ResultMap NormalError(HttpServletRequest request, Throwable ex) {
        return resultMap.fail().code(getStatus(request).value()).message(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultMap globalException(HttpServletRequest request, Throwable ex) {
        return resultMap.fail()
                .code(getStatus(request).value())
                .message("访问出错，无法访问: " + ex.getMessage());

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
