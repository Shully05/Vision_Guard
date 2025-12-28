package cn.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类（简化版）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor//自动生成构造方法以及getter setter
public class User {
    private int id;
    //主键id
    private String name;
    //用户姓名
    private String username;
    //账户
    private String password;
    //用户密码
    private String email;
    //邮箱
    private String intro;
    //介绍，简介
    private String phone;
    //手机号
    private Date create_time;
    //创建时间
    private Date update_time;
    //更新时间
    private String type;
    //用户类型
    private Integer pageNum=1;
    private Integer pageSize=5;
}