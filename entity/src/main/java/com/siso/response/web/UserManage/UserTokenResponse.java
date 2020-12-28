package com.siso.response.web.UserManage;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenResponse implements Serializable {
    private String number;
    private String authority;
    private String Superior_id;
    private String market;
    private String name;
}


