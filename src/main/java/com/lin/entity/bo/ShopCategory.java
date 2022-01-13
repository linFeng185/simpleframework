package com.lin.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author lin
 * @date 2022/1/13 22:03
 **/
@Data
public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
