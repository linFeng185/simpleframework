package com.lin.entity.dto;

import lombok.Data;

/**
 * @author lin
 * @date 2022/1/13 22:28
 **/
@Data
public class Result<T> {

    /**
     * 状态码，200为成功
     */
    private int code;
    /**
     * 返回提示
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;
}
