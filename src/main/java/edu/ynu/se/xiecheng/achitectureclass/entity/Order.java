package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="Order1")
@Where(clause = "is_deleted = 0")
public class Order extends LogicEntity {
    @Column
    private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shop shop;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LineItem> lineItems;
    public Order(){
        this.setState("未支付");
    }
    public Boolean isSameOrder(Order order){

        if(state.equals("未支付")&&
                order.state.equals("未支付")
                &&order.customer.equals(customer)
                &&order.shop.equals(shop))
            return true;
        return false;
    }
    public Boolean addNewLineItems(Set<LineItem> newLineItems){
        return lineItems.addAll(newLineItems);
    }
}
