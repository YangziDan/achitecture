package edu.ynu.se.xiecheng.achitectureclass.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpMessage<T> extends BaseMessage<T> {
    String token;
}
