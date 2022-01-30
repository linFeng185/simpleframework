package org.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ClassUtilTest {

    @Test
    void extractPackageClass() {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.lin.entity");
        System.out.println(classSet);
        Assertions.assertEquals(4,classSet.size());
    }

    @Test
    void loadClass() {
    }

    @Test
    void getClassLoader() {
    }

    @Test
    void main() {
    }
}
