package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import java.util.List;

public interface UserDao extends LogicDAO<User,Long> {

    public User getUserByAccount(String account);
}
