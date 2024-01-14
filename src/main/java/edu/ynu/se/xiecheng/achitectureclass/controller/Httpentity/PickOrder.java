package edu.ynu.se.xiecheng.achitectureclass.controller.Httpentity;

import edu.ynu.se.xiecheng.achitectureclass.entity.LineItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PickOrder {
    Long cus_id;
    Long shop_id;
    Long item_id;
    int quantity;
}
