package com.dirk.chat.controller;

import com.dirk.chat.pojo.User;
import com.dirk.chat.pojo.bo.UserBO;
import com.dirk.chat.service.UserService;
import com.dirk.chat.utils.FastDFSClient;
import com.dirk.chat.utils.FileUtils;
import com.dirk.chat.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dirk
 * @date 2020-06-07 10:29
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * @param user
     * @return RequestBody 用来接收前端传递给后端的json字符串中的数据
     */
    @PostMapping("/register")
    @ResponseBody
    public JSONResult register(@RequestBody User user) {

        // 判断用户名是否已被注册
        System.out.println("user = " + user);

        // 判断用户名、昵称、密码不能为空
        if (StringUtils.isEmpty(user.getNickname())
                || StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())) {

            return JSONResult.errorMsg("注册信息不能为空");
        }

        // 判断用户名是否已被注册
        boolean usernameIsExist = userService.usernameIsExist(user.getUsername());
        User userResult;
        if (usernameIsExist) {
            return JSONResult.errorMsg("用户名已被注册");
        } else {
            // 完成注册
            userResult = userService.register(user);
        }

        // 判断注册是否成功
        if (StringUtils.isEmpty(userResult)) {
            return JSONResult.errorMsg("注册失败，请检查网络");
        }

        return JSONResult.ok(userResult);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JSONResult login(@RequestBody User user) {

        System.out.println("user = " + user);

        // 判断用户名、密码是否为空
        if (StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())) {
            return JSONResult.errorMsg("登录信息不能为空");
        }

        // 判断用户名是否存在
        boolean usernameIsExist = userService.usernameIsExist(user.getUsername());
        User userResult;
        if (usernameIsExist) {
            // 完成登录
            userResult = userService.login(user);
        } else {
            return JSONResult.errorMsg("用户名不存在");
        }

        // 判断登录是否成功
        if (StringUtils.isEmpty(userResult)) {
            return JSONResult.errorMsg("密码错误");
        }

        return JSONResult.ok(userResult);
    }

    /**
     * 用户上传图片，图片以base64字符串形式保存
     *
     * @return
     */
    @PostMapping("/uploadFaceBase64")
    @ResponseBody
    public JSONResult uploadFaceBase64(@RequestBody UserBO userBO) {

        // 创建临时文件，存储base64字符串转为的图片
        String facePath = "./temp/" + userBO.getUserId() + ".png";
        // 大图url
        String faceImgBigUrl;
        try {
            // 将base64字符串转为图片
            FileUtils.base64ToFile(facePath, userBO.getFaceBase64Data());
            MultipartFile faceFile = FileUtils.fileToMultipart(facePath);
            faceImgBigUrl = fastDFSClient.uploadBase64(faceFile);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传失败，请检查网络");
        }

        // 更新用户头像
        User user = userService.updateUserFace(userBO.getUserId(), faceImgBigUrl);

        return JSONResult.ok(user);
    }

}
