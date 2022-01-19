package com.lin.designmode.factory.method;

import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 23:11
 **/
public class FactoryMethodDemo {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new HpMouseFactory();
        Mouse mouse = mouseFactory.createMouse();
        mouse.sayHiy();
    }
}
