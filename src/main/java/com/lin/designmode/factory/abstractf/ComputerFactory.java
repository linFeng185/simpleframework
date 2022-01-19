package com.lin.designmode.factory.abstractf;

import com.lin.designmode.factory.entity.Keyboard;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 22:51
 */
public interface ComputerFactory {
    Mouse createMouse();
    Keyboard createKeyboard();
}
