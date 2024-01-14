package edu.ynu.se.xiecheng.achitectureclass.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.common.utils.tokenUtil;
import edu.ynu.se.xiecheng.achitectureclass.dao.UserDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class UserService extends LogicService<UserDao, User, Long> {

    public UserService(@Autowired UserDao dao) {
        super(dao);
    }

    /**
     * TODO
     *
     * @param account
     * @param password
     * @return
     */
    @Transactional
    public String login(String account, String password) {
        User user = getDAO().getUserByAccount(account);
        if (user != null && user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return tokenUtil.token(account, password);
        }
        return null;
    }
    @Transactional
    public User checkToken(String token) {
        DecodedJWT jwt = tokenUtil.decode(token);
        //如果jwt解析失败则为空
        if (jwt == null) {
            //为空返回空
            return null;
        } else {
            //从token中解码出账户密码
            String account = jwt.getClaim("account").asString();
            String password = jwt.getClaim("password").asString();
            User user = getDAO().getUserByAccount(account);
            if (user == null)
                return null;
            //验证用户是否存在,若存在,验证密码是否正确
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))
                return user;
                //均正确则返回user,自动转换为json
            else
                return null;
            //不正确返回空
        }
    }
}
