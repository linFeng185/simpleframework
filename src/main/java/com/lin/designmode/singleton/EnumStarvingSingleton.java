package com.lin.designmode.singleton;

/**
 * 懒汉模式（加入了枚举）可以抵御反射与序列化的攻击
 * @author lin
 * @date 2022/1/29 21:53
 **/
public class EnumStarvingSingleton {
    private EnumStarvingSingleton(){}
    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private EnumStarvingSingleton instance;
        ContainerHolder(){
            instance = new EnumStarvingSingleton();
        }
    }
}
