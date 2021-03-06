package com.lin.controller.frontend;

import com.lin.entity.dto.MainPageInfoDTO;
import com.lin.entity.dto.Result;
import com.lin.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.croe.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lin
 * @date 2022/1/13 23:02
 **/
@Controller
@Getter
public class MainPageController {

    @Autowired("HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

    public void throwException(){
        throw new RuntimeException("抛出异常测试");
    }
}
