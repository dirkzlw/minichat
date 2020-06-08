package com.dirk.chat.service.impl;

import com.dirk.chat.pojo.User;
import com.dirk.chat.repository.UserRepository;
import com.dirk.chat.service.UserService;
import java.util.UUID;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-06-08 12:08
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 判断用户名是否已被注册
     * @param username
     * @return true 用户名存在
     */
    @Override
    public boolean usernameIsExist(String username) {

        User user = userRepository.findByUsername(username);

        return user == null ? false : true;
    }

    /**
     * 完成用户注册
     * @param user
     * @return
     */
    @Override
    public User register(User user) {

        // 生成用户id
        String userId = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(userId);
        user.setFaceImg("");
        user.setFaceImgBig("");
        user.setQrcode("");
        // 密码加密
        user.setPassword(encryptor.encrypt(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        User byUsername = userRepository.findByUsername(user.getUsername());
        if(user.getPassword().equals(encryptor.decrypt(byUsername.getPassword()))){
            return byUsername;
        }

        return null;
    }
}
