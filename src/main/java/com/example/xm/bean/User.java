package com.example.xm.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class User {
private int  uid;
    private String username    ;//姓名
    private String PASSWORD       ;//密码

    private String NAME          ;//真实姓名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday    ;//出生日期
    private String sex         ;//性别
    private String telephone    ;//手机号
    private String email          ;//邮箱
    private String  STATUS        ;//是否激活 Y激活 N未激活
    private String  CODE		;
    private String  check		;//验证码
}
