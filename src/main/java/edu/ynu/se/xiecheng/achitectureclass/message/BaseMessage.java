package edu.ynu.se.xiecheng.achitectureclass.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMessage<T>{
    protected Integer code=0;
    protected String msg="";
    protected T data;
}
