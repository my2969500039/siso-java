package com.siso.response.android.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class loginResponse implements Serializable
{
    private String token;
    private com.siso.response.android.login.userResponse userResponse;
}
