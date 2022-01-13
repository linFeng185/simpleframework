package com.lin.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author lin
 * @date 2022/1/13 22:03
 **/
@Data
public class HeadLine {
    private Long lineId;
    private String lineName;
    private  String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
