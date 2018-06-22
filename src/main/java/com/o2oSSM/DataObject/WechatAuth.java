package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 15:57
 * #微信账号
 */
@Data
public class WechatAuth {

    //微信id
    private Long wechatAuthId;

    //id
    private Long userId;

    //微信openid
    private String openId;

    private Date createDate;

    private PersonInfo personInfo;

}
