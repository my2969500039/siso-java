package com.siso.response.web.UserManage;


import com.siso.entity.web.permission.AdminPermission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginResponse implements Serializable {

    private String token;
    private UserTokenResponse userTokenResponse;
    private List<AdminPermission> adminPermission;


}
