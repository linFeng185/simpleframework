package com.lin.designmode.singleton;

/**
 * 懒汉模式
 * @author lin
 * @date 2022/1/29 21:54
 **/
public class StarvingSingleton {
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

    private StarvingSingleton(){ }

    public static StarvingSingleton getInstance(){
        return starvingSingleton;
    }
}
