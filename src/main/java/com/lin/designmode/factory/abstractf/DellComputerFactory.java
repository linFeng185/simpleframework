package com.lin.designmode.factory.abstractf;

import com.lin.designmode.factory.entity.DellKeyboard;
import com.lin.designmode.factory.entity.DellMouse;
import com.lin.designmode.factory.entity.Keyboard;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 23:29
 **/
public class DellComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
