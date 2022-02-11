package org.simpleframework.croe;

import com.lin.controller.DispatcherServlet;
import com.lin.controller.frontend.MainPageController;
import com.lin.service.solo.HeadLineService;
import com.lin.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.croe.annotation.Controller;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeanContainerTest {

    private static BeanContainer beanContainer;

    /**
     * 执行测试方法前初始化一次
     */
    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer：loadBeansTest")
    @Test
    @Order(1)
    public void loadBeansTest(){
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadBeans("com.lin");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }

    @Test
    @Order(2)
    @DisplayName("根据类获取bean实例")
    public void getBean(){
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true,mainPageController instanceof MainPageController);
//        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
//        Assertions.assertEquals(true,dispatcherServlet instanceof DispatcherServlet);
    }

    @Test
    @Order(3)
    @DisplayName("根据注解获取bean的Class对象集合")
    public void getClassesByAnnotation(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(3,beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @Test
    @Order(4)
    @DisplayName("根据接口获取其实现类")
    public void getClassesBySuper(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(true,beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }

    @Test
    void isLoaded() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void loadBeans() {
    }
}
