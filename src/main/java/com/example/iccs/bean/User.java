package com.example.iccs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author zsd
 * @date 2022/1/11
 */
@Data
public class User {
    private long uid;           //id
    private String username;    //姓名
    private String password;    //密码
    private String nickname;    //昵称
    private String sex;         //性别
    private String phone;       //电话号码
    private String email;       //邮箱
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;      //生日
    private String remarks;     //备注
    private String circleUrl;   //头像地址
}
