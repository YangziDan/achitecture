package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;
@Entity
@Getter
@Setter
@DiscriminatorValue("2")
@Where(clause = "is_deleted = 0")
public class Customer extends User{
    @OneToMany(mappedBy = "customer",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;
    public Boolean pay(Order order){
        if (!orders.contains(order))
            return false;
        if(order.getState().equals("未支付")){
            order.setState("已支付");
        }
        return true;
    }

}
