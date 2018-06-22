package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:00
 * #本地账号
 */
@Data
public class LocalAuth {

    private Long localAuthId;

    private String userName;

    private String password;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    private PersonInfo personInfo;
}
