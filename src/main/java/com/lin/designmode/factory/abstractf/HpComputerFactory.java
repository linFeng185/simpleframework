package com.lin.designmode.factory.abstractf;

import com.lin.designmode.factory.entity.HpKeyboard;
import com.lin.designmode.factory.entity.HpMouse;
import com.lin.designmode.factory.entity.Keyboard;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 23:30
 **/
public class HpComputerFactory implements ComputerFactory{
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}
