package org.simpleframework.croe;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.croe.annotation.Component;
import org.simpleframework.croe.annotation.Controller;
import org.simpleframework.croe.annotation.Repository;
import org.simpleframework.croe.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean容器
 * @author lin
 * @date 2022/1/29 22:35
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放所有被配置标记的目标对象的map
     */
    private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap();

    /**
     * 容器是否已经加载过bean
     */
    private boolean loaded = false;

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);

    /**
     * bean实例数量
     * @return
     */
    public int size(){
        return beanMap.size();
    }

    /**
     * 容器是否已经加载过bean
     * @return
     */
    public boolean isLoaded(){
        return loaded;
    }

    /**
     * 获取bean容器实例
     * @return
     */
    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder{
        HOLDER;
        private final BeanContainer instance;
        ContainerHolder(){
            instance = new BeanContainer();
        }
    }

    /**
     * 扫描加载所有bean
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName){
        if(isLoaded()){
            log.warn("BeanContainer has bean loaded.");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if(ValidationUtil.isEmpty(classSet)){
            log.warn("extract nothing from packageName:"+packageName);
            return;
        }
        for (Class<?> clazz : classSet){
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION){
                //如果类上面标记了定义的注解
                if(clazz.isAnnotationPresent(annotation)){
                    //将目标本身类作为键，目标类的实例作为值，存入到beanMap中
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }
            }
        }
        loaded = true;
    }
}
