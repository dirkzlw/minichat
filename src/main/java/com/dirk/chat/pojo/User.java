package com.dirk.chat.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-06-08 11:29
 * @description
 */
@Entity
@Table(name = "t_user")
// 对象转换成json是忽略空字段
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Getter
@Setter
@ToString
public class User {

    // 由UUID生成唯一用户ID，唯一，不为空
    @Id
    @Column(length = 64, unique = true, nullable = false)
    private String userId;
    // 用户名
    @Column(length = 50, unique = true, nullable = false)
    private String username;
    // 昵称
    @Column(length = 50, nullable = false)
    private String nickname;
    // 密码，由jasypt加密
    @Column(length = 100, nullable = false)
    private String password;
    // 头像及大头像，默认长度255
    @Column(nullable = false)
    private String faceImg;
    @Column(nullable = false)
    private String faceImgBig;
    // 用户二维码，默认长度255
    @Column(nullable = false)
    private String qrcode;
    // 手机的客户端ID
    @Column(length = 64)
    private String cid;

}
