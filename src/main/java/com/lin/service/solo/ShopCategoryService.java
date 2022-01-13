package com.lin.service.solo;

import com.lin.entity.bo.ShopCategory;
import com.lin.entity.dto.Result;

import java.util.List;

/**
 * @author lin
 * @date 2022/1/13 22:31
 **/
public interface ShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);
    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
