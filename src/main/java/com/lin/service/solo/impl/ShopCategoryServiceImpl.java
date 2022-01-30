package com.lin.service.solo.impl;

import com.lin.entity.bo.ShopCategory;
import com.lin.entity.dto.Result;
import com.lin.service.solo.ShopCategoryService;
import org.simpleframework.croe.annotation.Service;

import java.util.List;

/**
 * @author lin
 * @date 2022/1/13 22:33
 **/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
