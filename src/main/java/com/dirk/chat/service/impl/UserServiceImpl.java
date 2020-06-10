package com.dirk.chat.service.impl;

import com.dirk.chat.pojo.User;
import com.dirk.chat.repository.UserRepository;
import com.dirk.chat.service.UserService;
import java.util.UUID;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Value("${fdfs.file.url}")
    private String FASTDFS_FILE_URL;

    /**
     * 判断用户名是否已被注册
     *
     * @param username
     * @return true 用户名存在
     * Propagation.SUPPORTS 支持当前事务，如果当前没有事务，就以非事务方式执行。
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean usernameIsExist(String username) {

        User user = userRepository.findByUsername(username);

        return user == null ? false : true;
    }

    /**
     * 完成用户注册
     *
     * @param user
     * @return Propagation.REQUIRED 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User register(User user,String qrcodeUrl) {

        user.setFaceImg("");
        user.setFaceImgBig("");
        user.setQrcode(FASTDFS_FILE_URL + qrcodeUrl);
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
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User login(User user) {

        User byUsername = userRepository.findByUsername(user.getUsername());
        if (user.getPassword().equals(encryptor.decrypt(byUsername.getPassword()))) {
            return byUsername;
        }

        return null;
    }

    /**
     * 更新用户头像信息
     *
     * @param userId
     * @param faceImgBigUrl
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserFace(String userId, String faceImgBigUrl) {
        // 小图url
        String[] split = faceImgBigUrl.split("\\.");
        String faceImgUrl = split[0] + "_80x80." + split[1];

        // 更新用户信息并保存
        User user = userRepository.getOne(userId);
        user.setFaceImgBig(FASTDFS_FILE_URL + faceImgBigUrl);
        user.setFaceImg(FASTDFS_FILE_URL + faceImgUrl);
        userRepository.save(user);

        return user;
    }

    /**
     * 用户修改昵称
     * @param userId
     * @param nickname
     * @return
     */
    @Override
    public User resetNickname(String userId, String nickname) {
        // 更新用户信息并保存
        User user = userRepository.getOne(userId);
        user.setNickname(nickname);
        userRepository.save(user);

        return user;
    }
}
