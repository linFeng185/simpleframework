package com.lin.designmode.factory.method;

import com.lin.designmode.factory.entity.HpMouse;
import com.lin.designmode.factory.entity.Mouse;

/**
 * @author lin
 * @date 2022/1/18 22:59
 **/
public class HpMouseFactory implements MouseFactory {

    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
