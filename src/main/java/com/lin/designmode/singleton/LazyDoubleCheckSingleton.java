package com.lin.designmode.singleton;

import java.lang.reflect.InvocationTargetException;

/**
 * 双重校验锁检查（懒汉模式）
 * @author lin
 * @date 2022/1/29 21:54
 **/
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){

        //第一次检测
        if (instance==null){
            //同步
            synchronized (LazyDoubleCheckSingleton.class){
                if (instance == null){
                    //在new对象的时候，其实是分成了以下三步的操作，由于第二步和第三步的执行顺序是不确定的
                    // 所以可能会出现先给instance指向刚分配的内存地址，再初始化对象，所以instance需要以volatile关键字修饰
                    //而被volatile修饰的字段，会严格按照以下顺序创建对象
                    //memory = allocate(); //1.分配对象内存空间
                    //instance(memory);    //2.初始化对象
                    //instance = memory;   //3.设置instance指向刚分配的内存地址，此时instance！=null
                    instance = new LazyDoubleCheckSingleton();
                }

            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        System.out.println(LazyDoubleCheckSingleton.getInstance());
    }
}
