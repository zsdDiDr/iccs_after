package com.example.iccs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 *  服务器实体类
 *
 * @author zsd
 * @date 2022/1/14
 */
@Data
public class Server {

  private long sid;           //服务器id
  private String sname;       //服务器名
  private String token;       //令牌
  @DateTimeFormat(pattern="yyyy-MM-dd")
  @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
  private Date createDate;    //创建日期
  private String creator;     //创建人
  private String ip;          //ip地址
  private int count;       //设备数量


}
