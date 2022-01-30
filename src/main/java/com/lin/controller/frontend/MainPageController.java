package com.lin.controller.frontend;

import com.lin.entity.dto.MainPageInfoDTO;
import com.lin.entity.dto.Result;
import com.lin.service.combine.HeadLineShopCategoryCombineService;
import org.simpleframework.croe.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lin
 * @date 2022/1/13 23:02
 **/
@Controller
public class MainPageController {
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

    public void throwException(){
        throw new RuntimeException("抛出异常测试");
    }
}
