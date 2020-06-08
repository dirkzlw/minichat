package com.dirk.chat.service;

import com.dirk.chat.pojo.User;

/**
 * @author Dirk
 * @date 2020-06-08 12:07
 * @description
 */
public interface UserService {
    boolean usernameIsExist(String username);

    User register(User user);

    User login(User user);
}
