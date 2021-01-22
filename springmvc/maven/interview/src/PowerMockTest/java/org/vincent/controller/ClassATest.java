package org.vincent.controller;

import blog.csdn.net.ClassA;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Constructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ClassA.class})
public class ClassATest {

    @Test
    public void testInnerClass() throws Exception {
        String name = "mock name";

        Class clazz = Whitebox.getInnerClassType(ClassA.class, "InnerClassA");
        Constructor constructor = Whitebox.getConstructor(clazz, String.class);
        Object object = constructor.newInstance(name);
        String result = Whitebox.invokeMethod(object, "run");

        Assert.assertTrue(name.equalsIgnoreCase(result));
    }

}
