package com.lin.designmode.factory.entity;

/**
 * @author lin
 * @date 2022/1/18 23:22
 **/
public class HpKeyboard implements Keyboard{
    @Override
    public void sayHello() {
        System.out.println("我是惠普键盘");
    }
}
