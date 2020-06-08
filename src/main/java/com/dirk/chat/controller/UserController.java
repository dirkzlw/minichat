package com.dirk.chat.controller;

import com.dirk.chat.pojo.User;
import com.dirk.chat.service.UserService;
import com.dirk.chat.utils.JSONResult;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * @param user
     * @return
     *
     * RequestBody 用来接收前端传递给后端的json字符串中的数据
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
        User userResult ;
        if(usernameIsExist){
            return JSONResult.errorMsg("用户名已被注册");
        }else {
            // 完成注册
            userResult = userService.register(user);
        }

        // 判断注册是否成功
        if (StringUtils.isEmpty(userResult)) {
            return JSONResult.errorMsg("注册失败，请检查网络");
        }

        return JSONResult.ok(userResult);
    }

}
