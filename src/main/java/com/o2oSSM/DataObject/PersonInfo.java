package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 11:40
 * #用户信息
 */
@Data
public class PersonInfo {
    private Long userId;
    private String userName;
    private Date brithday;
    //性别
    private String gender;
    private String phone;
    private String email;
    //身份类别 1,顾客 2店家 3，超级管理员
    private String userType;
    //头像
    private String profilrImg;

    private Integer customerFlag;

    private Integer showOwnerFlag;

    private Integer adminFlag;

    //0禁止登陆  1可以登录
    private Integer enableStatus;

    private Date createTime;

    private Date updateTime;

}
