package com.lin.designmode.factory.simple;

import com.lin.designmode.factory.entity.DellMouse;
import com.lin.designmode.factory.entity.HpMouse;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 23:00
 **/
public class MouseFactory {

    public static Mouse  createMouse(int type){
        switch (type) {
            case 0: return new DellMouse();
            case 1: return new HpMouse();
            default: return new DellMouse();
        }
    }

    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse(1);
        mouse.sayHiy();
    }
}
