package edu.ynu.se.xiecheng.achitectureclass;
import edu.ynu.se.xiecheng.achitectureclass.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class BusinessTest {
    @Autowired
    BusinessService businessService;
    @Test
    void  confirm(){
        System.out.println(businessService.confirmOrder(1L,1L,8L));
    }
    @Test
    void  cancel(){
        businessService.cancelOrder(1L,1L,8L);
    }
}
