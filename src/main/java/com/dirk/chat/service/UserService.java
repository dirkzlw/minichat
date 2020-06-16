package com.dirk.chat.service;

import com.dirk.chat.pojo.User;
import com.dirk.chat.pojo.vo.FriendRequestVO;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-06-08 12:07
 * @description
 */
public interface UserService {
    boolean usernameIsExist(String username);

    User register(User user,String qrcodeUrl);

    User login(User user);

    User updateUserFace(String userId, String faceImgBigUrl);

    User resetNickname(String userId, String nickname);

    User findUserByUsername(String friendUsername);

    List<FriendRequestVO> findFriendRequestListByUserId(String userId);
}
