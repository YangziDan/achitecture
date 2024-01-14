package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.controller.Httpentity.LoginRequest;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import edu.ynu.se.xiecheng.achitectureclass.message.HttpMessage;
import edu.ynu.se.xiecheng.achitectureclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public HttpMessage<User> login(@RequestBody LoginRequest loginRequest) {
        HttpMessage<User> message=new HttpMessage<>();
        String account=loginRequest.getUsername();
        String password=loginRequest.getPassword();
        String token = userService.login(account, password);
        if (token==null){
            message.setMsg("密码错误");
            message.setCode(2);
            return message;
        }
        User user=userService.checkToken(token);
        if (user != null) {
            message.setToken(token);
            message.setCode(1);
            message.setData(user);
        }
        return message;
    }
    @PostMapping("/token")
    public HttpMessage<User> token(@RequestParam String token){
        HttpMessage<User> message=new HttpMessage<>();
        User user=userService.checkToken(token);
        if (user==null)
            return message;
        message.setData(user);
        message.setCode(1);
        return message;
    }
}
