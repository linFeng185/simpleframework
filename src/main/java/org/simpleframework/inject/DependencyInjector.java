package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.croe.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 该类提供依赖注入的服务
 * @author lin
 * @date 2022/2/10 22:19
 **/
@Slf4j
public class DependencyInjector {

    /**
     * bean容器实例
     */
    private BeanContainer beanContainer;

    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 对整个容器的class对象进行依赖注入处理
     */
    public void doIoc(){
        //遍历bean容器中的所有class对象
        Set<Class<?>> classes = beanContainer.getClasses();
        if(ValidationUtil.isEmpty(classes)){
            log.warn("empty classes in BeanContainer");
            return;
        }
        for (Class<?> clazz : classes){
            //遍历class对象中的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if(ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field : fields){
                //找出被@Autowired标记的成员变量
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredVal = autowired.value();
                    //获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //获取这些成员变量的类型在容器里对应的类型
                    Object fieldValue = getFileInstance(fieldClass,autowiredVal);
                    if(fieldValue == null){
                        throw new RuntimeException("依赖注入失败！无法从容器里获取到该类相关的类型"+fieldClass.getName());
                    }
                    //通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                    Object targetBean = beanContainer.getBean(clazz);
                    ClassUtil.setField(field,targetBean,fieldValue,true);
                }
            }

        }

    }

    /**
     * 根据class在beanContainer里获取其实例或实现类
     * @param fieldClass
     * @param autowiredVal
     * @return
     */
    private Object getFileInstance(Class<?> fieldClass,String autowiredVal) {
        //如果在容器里获取到了对应的bean实例，直接返回，否则根据传入的class对象获取其实现类
        Object fieldValue = beanContainer.getBean(fieldClass);
        if(fieldValue != null){
            return fieldClass;
        }
        Class<?> implementClass = getImplementClass(fieldClass,autowiredVal);
        if(implementClass != null){
            return beanContainer.getBean(implementClass);
        }
        return null;
    }

    /**
     * 根据class获取其实现类
     * @param fieldClass
     * @param autowiredVal 类名，用于应对有多个实现类的class的注入
     * @return
     */
    private Class<?> getImplementClass(Class<?> fieldClass,String autowiredVal) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (ValidationUtil.isEmpty(classSet)){
            return null;
        }
        //如果类名为空，则直接返回classSet中的值，否则遍历classSet中的Class对象，根据autowiredVal与类名做比较返回对应的实现类
        if(ValidationUtil.isEmpty(autowiredVal)){
            if(classSet.size() == 1){
                return classSet.iterator().next();
            }else {
                throw new RuntimeException(fieldClass.getName()+"类有多个实现类，可根据@Autowired中的value指定一个实现类进行注入。");
            }
        }
        for (Class<?> clazz : classSet){
            if(autowiredVal.equals(clazz.getSimpleName())){
                return clazz;
            }
        }
        return null;
    }
}
