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
import java.util.*;
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

    /**
     * 增加一个bean
     * @param clazz bean的class对象
     * @param bean 实例化的bean
     * @return
     */
    public Object addBean(Class<?> clazz,Object bean){
        return beanMap.put(clazz,bean);
    }

    /**
     * 删除一个bean
     * @param clazz bean的class对象
     * @return
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 根据class对象获取bean
     * @param clazz bean的class对象
     * @return
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取容器里所有的Class对象集合
     * @return
     */
    public Set<Class<?>>getClasses(){
        return beanMap.keySet();
    }

    /**
     * 获取容器里所有的bean
     * @return
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 通过注解筛选出容器里的Class对象
     * @param annotation
     * @return
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        //获取beanMap所有的class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //筛选出被该注解标记的Class对象，并放到结果集中
        Set<Class<?>> res = new HashSet<>();
        for (Class<?> clazz : keySet){
            if(clazz.isAnnotationPresent(annotation)){
                res.add(clazz);
            }
        }
        return res.isEmpty()?null:res;
    }

    /**
     * 通过接口或者父类获取其实现类或子类的class集合，不包括其本身
     * @param interfaceOrClass 接口class或者父类class
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        //获取beanMap所有的class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //筛选出接口的实现类或者类的子类的Class对象，并放到结果集中
        Set<Class<?>> res = new HashSet<>();
        for (Class<?> clazz : keySet){
            //判断keySet里的class对象是否是接口的实现类或者类的子类
            if(interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                res.add(clazz);
            }
        }
        return res.isEmpty()?null:res;
    }
}
