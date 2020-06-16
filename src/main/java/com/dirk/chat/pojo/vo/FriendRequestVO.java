package com.dirk.chat.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-06-12 11:06
 * @description
 */
@Getter
@Setter
public class FriendRequestVO {

    private Integer userId;

    private String username;

    private String nickname;

    private String faceImg;

}
