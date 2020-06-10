package com.dirk.chat.pojo.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-06-09 10:39
 * @description
 */
@Getter
@Setter
public class UserBO {

    private String userId;
    // 用户昵称
    private String nickname;
    // 用户头像，格式为base64
    private String faceBase64Data;

}
