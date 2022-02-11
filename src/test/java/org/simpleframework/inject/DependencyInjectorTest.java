package org.simpleframework.inject;

import com.lin.controller.frontend.MainPageController;
import com.lin.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import com.lin.service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.croe.BeanContainer;

import static org.junit.jupiter.api.Assertions.*;

class DependencyInjectorTest {

    @Test
    @DisplayName("依赖注入测试")
    void doIco(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.lin");
        Assertions.assertEquals(true,beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true,mainPageController instanceof MainPageController);
        Assertions.assertEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
        Assertions.assertEquals(true,mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
        Assertions.assertEquals(false,mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);
    }

}
