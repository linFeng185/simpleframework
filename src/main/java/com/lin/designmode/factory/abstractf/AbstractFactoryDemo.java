package com.lin.designmode.factory.abstractf;

import com.lin.designmode.factory.entity.Keyboard;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 23:32
 **/
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        ComputerFactory computerFactory = new HpComputerFactory();
        Mouse mouse = computerFactory.createMouse();
        Keyboard keyboard = computerFactory.createKeyboard();
        mouse.sayHiy();
        keyboard.sayHello();
    }
}
