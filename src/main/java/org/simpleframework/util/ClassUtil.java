package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 该工具类主要完成：
 * 1、获取到类的加载器
 * 2、通过类加载器获取到加载的资源信息
 * 3、依据不同的资源类型，采取不同的方式获取资源的集合
 * @author lin
 * @date 2022/1/20 22:59
 **/
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
        //获取到类的加载器
        ClassLoader classLoader = getClassLoader();
        //通过类加载器获取到加载的资源
        URL url = classLoader.getResource(packageName.replace(".","/"));
        if(url == null){
            log.warn("无法解析包路径："+packageName);
            return null;
        }
        //依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        //过滤出文件类型的资源
        if(url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<>();
            //获取到目录
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet,packageDirectory,packageName);
        }
        return classSet;
    }

    /**
     * 递归获取目标package里面的所有class文件（包括子目录的文件）
     * @param emptyClassSet 装载目标类的集合
     * @param fileSource 文件或者目录
     * @param packageName 包名
     * @return 类集合
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        //如果不是目录的话
        if(!fileSource.isDirectory()){
            return;
        }
        //如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件或文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()){
                    return true;
                }else {
                    //获取文件的绝对值路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        //若是class文件，则直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }

            /**
             * 根据class文件的绝对路径，生成class对象，并放入到classSet中
             * @param absoluteFilePath
             */
            private void addToClassSet(String absoluteFilePath) {
                //从class文件的绝对路径里提取出包含了package的类名
                //通过反射机制获取对应的Class对象并加入到classSet里
                //如D:\projects\simpleframework\src\main\java\com\lin\entity\dto\MainPageInfoDTO.java
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));
                //需要弄成com.lin.entity.dto.MainPageInfoDTO
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });
        if (files != null){
            for (File f : files){
                extractClassFile(emptyClassSet,f,packageName);
            }
        }
    }

    /**
     * 获取class对象
     * @param className class全名，class类名+package名
     * @return
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        //程序是通过线程来执行的，获取到当前执行的方法的线程，通过线程所属的加载器获取到程序的资源信息
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 实例化class
     * @param clazz class的类型
     * @param accessible 是否支持调用私有的构造函数
     * @param <T>
     * @return 实例化的类
     */
    public static<T> T newInstance(Class<?> clazz,boolean accessible){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置类的属性值
     * @param field 成员变量
     * @param target 类实例
     * @param value 成员变量值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field,Object target,Object value,boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target,value);
        } catch (IllegalAccessException e) {
            log.error("设置类的属性值异常：",e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        extractPackageClass("com.lin.entity");
    }
}
