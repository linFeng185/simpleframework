package com.lin.service.combine;

import com.lin.entity.dto.MainPageInfoDTO;
import com.lin.entity.dto.Result;

/**
 * @author lin
 * @date 2022/1/13 22:35
 **/
public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
