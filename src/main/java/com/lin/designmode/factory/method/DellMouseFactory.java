package com.lin.designmode.factory.method;

import com.lin.designmode.factory.entity.DellMouse;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 22:58
 **/
public class DellMouseFactory implements MouseFactory {

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
